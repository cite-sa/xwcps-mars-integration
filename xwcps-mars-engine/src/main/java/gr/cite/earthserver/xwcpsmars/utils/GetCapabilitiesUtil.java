package gr.cite.earthserver.xwcpsmars.utils;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class GetCapabilitiesUtil {
	private static final String GET_CAPABILITIES_COVERAGE_SUMMARY_START = "$$CoverageSummaryStart$$";
	private static final String GET_CAPABILITIES_COVERAGE_SUMMARY_END = "$$CoverageSummaryEnd$$";
	private static final String GET_CAPABILITIES_COVERAGE_SUMMARY_COVERAGE_ID = "$$CoverageId$$";
	
	public static String generateDocument(List<String> coverageIds) throws IOException {
		
		String getCapabilitiesTemplateContent = Resources.toString(Resources.getResource("GetCapabilities_template.xml"), StandardCharsets.UTF_8);
		
		int coverageSummaryStartIndex = getCapabilitiesTemplateContent.indexOf(GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_START);
		int coverageSummaryEndIndex = getCapabilitiesTemplateContent.indexOf(GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_END) + GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_END.length();
		
		int coverageSummaryElementStartIndex = getCapabilitiesTemplateContent.indexOf(GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_START) + GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_START.length();
		int coverageSummaryElementEndIndex = getCapabilitiesTemplateContent.indexOf(GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_END);
		String coverageSummaryElementTemplate = getCapabilitiesTemplateContent.substring(coverageSummaryElementStartIndex, coverageSummaryElementEndIndex);
		
		String coverageSummaries = coverageIds.stream()
				.map(coverageId -> coverageSummaryElementTemplate.replace(GetCapabilitiesUtil.GET_CAPABILITIES_COVERAGE_SUMMARY_COVERAGE_ID, coverageId))
				.collect(Collectors.joining(""));
		
		String startFragment = getCapabilitiesTemplateContent.substring(0, coverageSummaryStartIndex);
		String endFragment = getCapabilitiesTemplateContent.substring(coverageSummaryEndIndex, getCapabilitiesTemplateContent.length());
		
		return startFragment + coverageSummaries + endFragment;

	}
}
