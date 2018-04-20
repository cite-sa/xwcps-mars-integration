package gr.cite.earthserver.xwcpsmars.application.resources;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.base.Strings;
import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponse;
import gr.cite.earthserver.xwcpsmars.application.dto.XwcpsMarsResponseEntity;
import gr.cite.earthserver.xwcpsmars.exceptions.XwcpsMarsException;
import gr.cite.earthserver.xwcpsmars.application.request.LoggedRequest;
import gr.cite.earthserver.xwcpsmars.application.request.RequestsReport;
import gr.cite.earthserver.xwcpsmars.mars.MarsClientAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Path("reporting")
@Produces(MediaType.APPLICATION_JSON)
public class LogReportingResource {
	private static final Logger logger = LoggerFactory.getLogger(LogReportingResource.class);
	
	private ConcurrentMap<String, RequestsReport> reports = new ConcurrentHashMap<>();
	
	
	private enum RequestStep {
		NONE, OTHER, QUERY_TRANSLATION_EXECUTION_TIME, MARS_REQUEST, MARS_REQUEST_EXECUTION_TIME, RASDAMAN_REGISTRATION_EXECUTION_TIME, WCS_REQUEST, WCS_REQUEST_EXECUTION_TIME
	}
	
	private static final String DATE_TIME = "dateTime";
	private static final String REQUEST_ID = "requestId";
	private static final String STEP = "step";
	private static final String EXECUTION_TIME = "executionTime";
	private static final String REQUEST = "request";
	
	//private static final String uuidRegExp = "((?<" + DATE_TIME+ ">\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}).+ - )\\[(?<" + REQUEST_ID + ">[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})[_ingredient.json]*] ((?<" + STEP + ">.+)\\[(?<" + EXECUTION_TIME + ">[0-9]+) ms]|WCS request \\[(?<" + WCS_REQUEST + ">.+)]|MARS retrieval \\[(?<" + MARS_REQUEST + ">.+)])";
	//private static final String uuidRegExp = "((?<" + DATE_TIME + ">\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}).+ - )\\[(?<" + REQUEST_ID + ">[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})[_ingredient.json]*] (?<" + STEP + ">.+) (\\[(?<" + EXECUTION_TIME + ">[0-9]+) ms]|\\[(?<" + REQUEST + ">.+)])";
	private static final String uuidRegExp = "(?<" + DATE_TIME + ">\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2},\\d{3}).+ - \\[(?<" + REQUEST_ID + ">[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12})[_ingredient.json]*\\] (?<" + STEP + ">Query translation time|MARS retrieval execution time|MARS retrieval|Rasdaman ingestion execution time|WCS request execution time|WCS request) (\\[(?<" + EXECUTION_TIME + ">[0-9]+) ms\\]|\\[(?<" + REQUEST + ">.+)\\])";
	private final Pattern pattern;
	
	private String logsBasePath;
	private String logsSubDirectory;
	private String logsPrefix;
	private String hostname;
	
	private MarsClientAPI marsClient;
	
	@Inject
	public LogReportingResource(String logsBasePath, String logsSubDirectory, String logsPrefix, MarsClientAPI marsClient, String hostname) {
		this.logsBasePath = logsBasePath;
		this.logsSubDirectory = logsSubDirectory;
		this.logsPrefix = logsPrefix;
		this.hostname = hostname;
		
		this.marsClient = marsClient;
		
		this.pattern = Pattern.compile(uuidRegExp);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, "text/csv"})
	public Response getLoggedRequests(@QueryParam("log") String logFilename,
										   @QueryParam("from") String from, @QueryParam("to") String to,
										   @QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset,
										   @DefaultValue ("text/csv") @HeaderParam(HttpHeaders.CONTENT_TYPE) String contentType,
										   @Context UriInfo uriInfo) throws XwcpsMarsException {
		
		String reportId = UUID.randomUUID().toString();
		RequestsReport report = new RequestsReport();
		report.setContentType(contentType);
		this.reports.putIfAbsent(reportId, report);
		
		logger.debug("Content-Type is: " + contentType);
		
		boolean async = uriInfo.getQueryParameters().containsKey("async");
		if (!async) {
			generateReport(reportId, logFilename, from, to, limit, offset);
			return getReport(reportId);
		} else {
			Thread marsThread = new Thread(() -> {
				try {
					generateReport(reportId, logFilename, from, to, limit, offset);
				} catch (XwcpsMarsException e) {
					throw new RuntimeException(e);
				}
			});
			marsThread.start();
			return Response.temporaryRedirect(UriBuilder.fromPath(this.hostname).path(LogReportingResource.class).path(reportId).build()).build();
		}
	}
	
	@GET
	@Path("{reportId}")
	@Produces({MediaType.APPLICATION_JSON, "text/csv"})
	public Response getReport(@PathParam("reportId") String reportId) throws XwcpsMarsException {
		if (!this.reports.containsKey(reportId)) throw new NoSuchElementException("[" + reportId + "] No such response in processing");
	
		RequestsReport report = this.reports.get(reportId);
		if (report.getRequests() == null) {
			XwcpsMarsResponse<List<LoggedRequest>> response = new XwcpsMarsResponse<>();
			
			response.setMessage("[" + reportId + "] Report not ready yet");
			response.setStatus(Response.Status.ACCEPTED.getStatusCode());
			
			return Response.accepted().entity(response).build();
		} else {
			return buildReportResponse(reportId, report);
		}
	}
	
	private Response buildReportResponse(String reportId, RequestsReport report) {
		String contentType = report.getContentType();
		List<LoggedRequest> requests = new ArrayList<>(report.getRequests());
		
		this.reports.remove(reportId);
		
		if ("text/csv".equals(contentType)) {
			return serializeAsCsv(requests);
		} else if (MediaType.APPLICATION_JSON.equals(contentType))  {
			XwcpsMarsResponse<List<LoggedRequest>> response = new XwcpsMarsResponse<>();
			XwcpsMarsResponseEntity<List<LoggedRequest>> entity = new XwcpsMarsResponseEntity<>();
			
			entity.setBody(requests);
			
			response.setMessage(report.getRequests().size() + " requests found");
			response.setStatus(Response.Status.OK.getStatusCode());
			response.setEntity(entity);
			
			return Response.ok(response).build();
		} else {
			throw new IllegalArgumentException("Content-Type should be either " + MediaType.APPLICATION_JSON + " or text/csv");
		}
	}
	
	private Response serializeAsCsv(List<LoggedRequest> requests) {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(LoggedRequest.class).withHeader();
		ObjectWriter writer = mapper.writerFor(LoggedRequest.class).with(schema);
		
		StreamingOutput stream = output -> {
			try {
				writer.writeValues(output).writeAll(requests);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			}
		};
		
		return Response.ok(stream, "text/csv")
				   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + "report_" + Instant.now().toString() + ".csv")
				   .build();
	}
	
	private void generateReport(String reportId, String logFilename, String from, String to, Integer limit, Integer offset) throws XwcpsMarsException {
		Map<String, LoggedRequest> loggedRequests = new HashMap<>();
		
		Instant fromInstant = parseFromParam(from);
		Instant toInstant = parseToParam(to);
		
		List<java.nio.file.Path> logFiles = buildLogFilesList(logFilename);
		
		for (java.nio.file.Path logFile: logFiles) {
			parseFile(logFile, fromInstant, toInstant, loggedRequests);
		}
		
		applyLimitAndOffset(reportId, limit, offset, loggedRequests);
	}
	
	private Instant parseFromParam(String from) {
		Instant fromInstant = null;
		try {
			if (from != null) {
				try {
					fromInstant = Instant.parse(appendUtcZone(from));
				} catch (Exception e) {
					fromInstant = LocalDate.parse(from).atStartOfDay().toInstant(ZoneOffset.UTC);
				}
			}
		} catch (Exception e) {
			logger.error("[" + from  + "] Invalid date format", e);
			throw new IllegalArgumentException("[" + from + "] Invalid date format", e);
		}
		return fromInstant;
	}
	
	private Instant parseToParam(String to) {
		Instant toInstant = null;
		try {
			if (to != null) {
				try {
					toInstant = Instant.parse(appendUtcZone(to));
				} catch (Exception e) {
					toInstant = LocalDate.parse(to).atStartOfDay().plusHours(24).toInstant(ZoneOffset.UTC);
				}
			}
		} catch (Exception e) {
			logger.error("[" + to + "] Invalid date format", e);
			throw new IllegalArgumentException("[" + to + "] Invalid date format", e);
		}
		return toInstant;
	}
	
	private String appendUtcZone(String date) {
		return date.endsWith("Z") ? date : date + "Z";
	}
	
	private List<java.nio.file.Path> buildLogFilesList(String logFilename) {
		List<java.nio.file.Path> logPaths;
		try {
			if (Strings.isNullOrEmpty(logFilename)) {
				logPaths = findLogFiles();
			} else {
				logPaths = new ArrayList<>();
				if (Files.exists(Paths.get(this.logsBasePath, logFilename))) logPaths.add(Paths.get(this.logsBasePath, logFilename));
			}
		} catch (IOException e) {
			logger.error("Unable to detect log files", e);
			throw new IllegalArgumentException("Unable to detect log files", e);
		}
		return logPaths;
	}
	
	private List<java.nio.file.Path> findLogFiles() throws IOException {
		Stream<java.nio.file.Path> mainLogs = Stream.empty(), archivedLogs = Stream.empty();
		Predicate<java.nio.file.Path> filterByLogPrefix = path -> path.getFileName().toString().startsWith(this.logsPrefix);
		
		if (Files.exists(Paths.get(this.logsBasePath))) {
			mainLogs = Files.list(Paths.get(this.logsBasePath)).filter(filterByLogPrefix);
		}
		
		java.nio.file.Path subdirectoryPath = Paths.get(this.logsBasePath, this.logsSubDirectory);
		if (Files.exists(subdirectoryPath)) {
			archivedLogs = Files.list(subdirectoryPath).filter(filterByLogPrefix);
		}
		
		return Stream.concat(mainLogs, archivedLogs).collect(Collectors.toList());
	}
	
	private void parseFile(java.nio.file.Path logPath, Instant fromInstant, Instant toInstant, Map<String, LoggedRequest> loggedRequests) throws XwcpsMarsException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(logPath.toFile());
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				
				Matcher matcher = this.pattern.matcher(line);
				if (matcher.find()) {
					parseLine(matcher, fromInstant, toInstant, loggedRequests);
				}
			}
			
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} catch (IOException e) {
			logger.error("[" + logPath + "] Error reading log file", e);
			throw new XwcpsMarsException("[" + logPath + "] Error reading log file", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					logger.error("[" + logPath + "] Error closing input stream", e);
				}
			}
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	private void parseLine(Matcher matcher, Instant from, Instant to, Map<String, LoggedRequest> loggedRequests) {
		String requestId = getRequestId(matcher);
		
		if (requestId != null) {
			
			Instant dateTime = getDateTime(matcher);
			if (from != null && dateTime != null && dateTime.isBefore(from)) return;
			if (to != null && dateTime != null && dateTime.isAfter(to)) return;
			
			if (!loggedRequests.containsKey(requestId)) {
				LoggedRequest request = new LoggedRequest();
				request.setId(requestId);
				loggedRequests.put(requestId, request);
			}
			LoggedRequest request = loggedRequests.get(requestId);
			
			switch (getStep(matcher)) {
				case QUERY_TRANSLATION_EXECUTION_TIME:
					request.setRequestTranslationTime(getExecutionTime(matcher));
					request.setStartTime(dateTime);
					break;
				case MARS_REQUEST:
					String marsRequest = getRequest(matcher);
					if (marsRequest != null) {
						request.setMarsRequest(marsRequest);
					}
					break;
				case MARS_REQUEST_EXECUTION_TIME:
					request.setMarsRequestTime(getExecutionTime(matcher));
					break;
				case RASDAMAN_REGISTRATION_EXECUTION_TIME:
					request.setRasdamanRegistrationTime(getExecutionTime(matcher));
					break;
				case WCS_REQUEST:
					String wcsRequest = getRequest(matcher);
					if (wcsRequest != null && wcsRequest.toLowerCase().contains("service=wcs&version=2.0.1&request=")) {
						request.setWcsRequest(wcsRequest);
					}
					break;
				case WCS_REQUEST_EXECUTION_TIME:
					request.setWcsRequestTime(getExecutionTime(matcher));
					request.setEndTime(dateTime);
					break;
				default:
					break;
			}
			
			/*try {
				request.setMarsRequestActualExecutionTime(this.marsClient.calculateRequestTime(requestId));
			} catch (MarsClientException e) {
				logger.error(e.getMessage(), e);
			}*/
		}
	}
	
	private String getRequestId(Matcher matcher) {
		return matcher.group(REQUEST_ID);
	}
	
	private Instant getDateTime(Matcher matcher) {
		String dateTimeString = matcher.group(DATE_TIME);
		if (dateTimeString == null) return null;
		
		dateTimeString = dateTimeString.replace(" ", "T").replace(",", ".") + "Z";
		return Instant.parse(dateTimeString);
	}
	
	private RequestStep getStep(Matcher matcher) {
		return mapLogStepValueToRequestStep(matcher.group(STEP));
	}
	
	private RequestStep mapLogStepValueToRequestStep(String logStepValue) {
		if (Strings.isNullOrEmpty(logStepValue)) return RequestStep.NONE;
		
		if (logStepValue.contains("Query translation time")) {
			return RequestStep.QUERY_TRANSLATION_EXECUTION_TIME;
		}  else if (logStepValue.contains("MARS retrieval execution time")) {
			return RequestStep.MARS_REQUEST_EXECUTION_TIME;
		} else if (logStepValue.contains("MARS retrieval")) {
			return RequestStep.MARS_REQUEST;
		}  else if (logStepValue.contains("Rasdaman ingestion execution time")) {
			return RequestStep.RASDAMAN_REGISTRATION_EXECUTION_TIME;
		}  else if (logStepValue.contains("WCS request execution time")) {
			return RequestStep.WCS_REQUEST_EXECUTION_TIME;
		}  else if (logStepValue.contains("WCS request")) {
			return RequestStep.WCS_REQUEST;
		} else {
			return RequestStep.OTHER;
		}
	}
	
	private Long getExecutionTime(Matcher matcher) {
		String executionTimeString = matcher.group(EXECUTION_TIME);
		if (executionTimeString == null) return null;
		
		return Long.parseLong(executionTimeString);
	}
	
	private String getRequest(Matcher matcher) {
		return matcher.group(REQUEST);
	}
	
	private void applyLimitAndOffset(String reportId, Integer limit, Integer offset, Map<String, LoggedRequest> loggedRequests) {
		logger.info(loggedRequests.size() + " logged requests found before validation");
		
		List<LoggedRequest> sortedRequests = sortRequestsAndApplyLimitAndOffset(loggedRequests, limit, offset);
		
		RequestsReport report = this.reports.get(reportId);
		report.setRequests(sortedRequests);
		
		logger.info(sortedRequests.size() + " logged requests found after validation");
		
		/*if ("text/csv".equals(contentType)) {
			return serializeAsCsv(sortedRequests);
		} else if (MediaType.APPLICATION_JSON.equals(contentType))  {
			XwcpsMarsResponse<List<LoggedRequest>> response = new XwcpsMarsResponse<>();
			XwcpsMarsResponseEntity<List<LoggedRequest>> entity = new XwcpsMarsResponseEntity<>();
			
			entity.setBody(sortedRequests);
			
			response.setMessage(sortedRequests.size() + " requests found");
			response.setStatus(Response.Status.OK.getStatusCode());
			response.setEntity(entity);
			
			return Response.ok(response).build();
		} else {
			throw new IllegalArgumentException("Content-Type should be either " + MediaType.APPLICATION_JSON + " or text-csv");
		}*/
	}
	
	private List<LoggedRequest> sortRequestsAndApplyLimitAndOffset(Map<String, LoggedRequest> loggedRequests, Integer limit, Integer offset) {
		Stream<LoggedRequest> sortedRequestsStream = loggedRequests.values().stream()
														 .filter(LoggedRequest::isCompleted)
														 .sorted(Comparator.comparing(LoggedRequest::getStartTime));
		if (offset != null) {
			sortedRequestsStream = sortedRequestsStream.skip(offset);
		}
		if (limit != null) {
			sortedRequestsStream = sortedRequestsStream.limit(limit);
		}
		return sortedRequestsStream.collect(Collectors.toList());
	}
	
	
	
}
