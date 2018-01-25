package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.*;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistry;
import gr.cite.earthserver.xwcpsmars.utils.AxisUtils;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest.MarsRequestBuilder;
import gr.cite.earthserver.xwcpsmars.registry.CoverageRegistryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class WCPSEvalVisitor extends XWCPSParseTreeVisitor {
    private static final Logger logger = LoggerFactory.getLogger(WCPSEvalVisitor.class);

    private static final String LATITUDE_AXIS = "Lat";
    private static final String LONGITUDE_AXIS = "Long";
    private static final String DATE_TIME_AXIS = "reftime";

    private CoverageRegistry coverageRegistry;

    private MarsRequestBuilder marsRequestBuilder;

    private String coverageId;
    private String formatName;
    private AxisUtils.CoordinatesAggregator coordinatesAggregator;
    private AxisUtils.DateTimeTransformation dateTimeTransformation;
    private AxisUtils.AxisRangeAggregator axisRangeAggregator;

    private String timeAxisName;

    public WCPSEvalVisitor() {

    }

    public WCPSEvalVisitor(CoverageRegistry coverageRegistry) {
        this.coverageRegistry = coverageRegistry;
    }

    public String getCoverageId() {
        return this.coverageId;
    }
    
	public String getFormatName() {
		return this.formatName;
	}

    @Override public MarsRequestBuilder visitSpecificIdLabel(SpecificIdLabelContext ctx) {
        this.coverageId = ctx.COVERAGE_VARIABLE_NAME().getText();
        this.marsRequestBuilder = MarsRequest.builder(this.coverageId);
        return visitChildren(ctx);
    }

    @Override
    public MarsRequestBuilder visitEncodedCoverageExpressionLabel(EncodedCoverageExpressionLabelContext ctx) {
        visitChildren(ctx);
        return this.marsRequestBuilder;
    }

    @Override
    public MarsRequestBuilder visitCoverageExpressionShorthandTrimLabel(CoverageExpressionShorthandTrimLabelContext ctx) {
        /*for (int i = 0; i < ctx.getChildCount(); i ++) {
            System.out.println(ctx.getChild(i).getText());
        }*/
        return visitChildren(ctx);
    }


    @Override
    public MarsRequestBuilder visitDimensionIntervalListLabel(DimensionIntervalListLabelContext ctx) {
        this.coordinatesAggregator = new AxisUtils.CoordinatesAggregator();
        this.dateTimeTransformation = new AxisUtils.DateTimeTransformation();

        visitChildren(ctx);
        buildMarsRequest();

        this.coordinatesAggregator = null;
        this.dateTimeTransformation = null;

        return this.marsRequestBuilder;
    }

    @Override
    public MarsRequestBuilder visitTrimDimensionIntervalElementLabel(TrimDimensionIntervalElementLabelContext ctx) {
        processDimensionElements(ctx.axisName().getText(), ctx.coverageExpression().stream().map(CoverageExpressionContext::getText).collect(Collectors.toList()));
        visitChildren(ctx);
        return this.marsRequestBuilder;
    }

    @Override
    public MarsRequestBuilder visitSliceDimensionIntervalElementLabel(SliceDimensionIntervalElementLabelContext ctx) {
        processDimensionElements(ctx.axisName().getText(), Collections.singletonList(ctx.coverageExpression().getText()));
        visitChildren(ctx);
        return this.marsRequestBuilder;
    }

    @Override
    public MarsRequestBuilder visitDimensionPointListLabel(DimensionPointListLabelContext ctx) {
        this.coordinatesAggregator = new AxisUtils.CoordinatesAggregator();
        this.dateTimeTransformation = new AxisUtils.DateTimeTransformation();

        visitChildren(ctx);
        buildMarsRequest();

        this.coordinatesAggregator = null;
        this.dateTimeTransformation = null;

        return this.marsRequestBuilder;
    }

    @Override
    public MarsRequestBuilder visitDimensionPointElementLabel(DimensionPointElementLabelContext ctx) {
        processDimensionElements(ctx.axisName().getText(), Collections.singletonList(ctx.coverageExpression().getText()));
        visitChildren(ctx);
        return this.marsRequestBuilder;
    }

    private void processDimensionElements(String axisName, List<String> coverageExpressions) {
        if (WCPSEvalVisitor.LATITUDE_AXIS.equals(axisName)) {
            coverageExpressions.forEach(lat -> this.coordinatesAggregator.addLatitude(lat));
        } else if (WCPSEvalVisitor.LONGITUDE_AXIS.equals(axisName)) {
            coverageExpressions.forEach(lon -> this.coordinatesAggregator.addLongitude(lon));
        /*} else if (WCPSEvalVisitor.DATE_TIME_AXIS.equals(axisName)) {
            coverageExpressions.stream()
                    .map(dateTime -> dateTime.replaceFirst("^\"", "").replaceFirst("\"$", ""))
                    .forEach(this.dateTimeTransformation::parseDateTime);
        }*/
        } else {
            coverageExpressions = coverageExpressions.stream().map(dateTime -> dateTime.replaceFirst("^\"", "").replaceFirst("\"$", "")).collect(Collectors.toList());
            if (AxisUtils.DateTimeTransformation.isValidDateTime(coverageExpressions.get(0))) {
                this.timeAxisName = axisName;
                coverageExpressions.forEach(this.dateTimeTransformation::parseDateTime);
            } else {
                // TODO process other axes
                this.axisRangeAggregator = new AxisUtils.AxisRangeAggregator();
                List<Integer> rangeSteps = null;
                try {
                    /*rangeSteps = this.coverageRegistry.retrieveAxisDiscreteValues(this.coverageId, axisName).stream()
							.map(Integer::parseInt).collect(Collectors.toList());*/
                    rangeSteps = retrieveAxisDiscreteValues(this.coverageId, axisName);
                } catch (CoverageRegistryException e) {
                    logger.error(e.getMessage(), e);
                }
                coverageExpressions.stream().map(Integer::parseInt).forEach(this.axisRangeAggregator::addRangeLimit);
                this.axisRangeAggregator.limitAxisRangeSteps(rangeSteps);
                this.marsRequestBuilder.mapAxisNameToMarsField(axisName, this.axisRangeAggregator.stringifyAndGetLimitedRangeSteps());
            }
        }
    }

    private List<Integer> retrieveAxisDiscreteValues(String coverageId, String axisName) throws CoverageRegistryException {
        Integer origin = Integer.parseInt(this.coverageRegistry.retrieveAxisOriginPoint(coverageId, axisName));
        List<String> coefficients = this.coverageRegistry.retrieveAxisCoefficients(coverageId, axisName);

        return coefficients.stream().map(Integer::parseInt).map(coefficient -> origin + coefficient).collect(Collectors.toList());
    }

    private void buildMarsRequest() {
        this.marsRequestBuilder.area(this.coordinatesAggregator.buildMarsArea());

        this.marsRequestBuilder.date(this.dateTimeTransformation.buildMarsDate());
        if (this.dateTimeTransformation.isDateRange()) {
            AxisUtils.DateTimeUtil dateTimeUtil = new AxisUtils.DateTimeUtil();
            try {
                dateTimeUtil.parseMarsDateTimeRange(this.coverageRegistry.retrieveAxisOriginPoint(this.coverageId, this.timeAxisName),
                        this.coverageRegistry.retrieveAxisCoefficients(this.coverageId, this.timeAxisName));
            } catch (CoverageRegistryException e) {
                logger.error(e.getMessage(), e);
            }
            marsRequestBuilder.time(dateTimeUtil.buildMarsRequestTimeSteps());
        } else {
            marsRequestBuilder.time(dateTimeTransformation.buildMarsTime());
        }
    }
    
    @Override
    public MarsRequestBuilder visitFormat_name(Format_nameContext ctx) {
    	this.formatName = ctx.getText();
		return visitChildren(ctx);
    }
    
    /*@Override
    public MarsRequest visitXwcps(XwcpsContext ctx) {
        return visitChildren(ctx);
    }*/

}
