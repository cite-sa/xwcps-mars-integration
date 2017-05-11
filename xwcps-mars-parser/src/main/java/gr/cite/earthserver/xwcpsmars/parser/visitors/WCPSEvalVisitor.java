package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.*;
import gr.cite.earthserver.xwcpsmars.mars.request.CoordinatesAggregator;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WCPSEvalVisitor extends XWCPSParseTreeVisitor {
    private static final Logger logger = LoggerFactory.getLogger(WCPSEvalVisitor.class);

    private static final String LATITUDE_AXIS = "Lat";
    private static final String LONGITUDE_AXIS = "Long";
    private static final String DATE_TIME_AXIS = "ansi";

    private MarsRequest marsRequest;

    private String coverageId;
    private CoordinatesAggregator coordinatesAggregator;

    public WCPSEvalVisitor() {
        this.marsRequest = new MarsRequest();
    }

    public String getCoverageId() {
        return this.coverageId;
    }

    @Override public MarsRequest visitSpecificIdLabel(SpecificIdLabelContext ctx) {
        this.coverageId = ctx.COVERAGE_VARIABLE_NAME().getText();
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitEncodedCoverageExpressionLabel(EncodedCoverageExpressionLabelContext ctx) {
        visitChildren(ctx);
        return this.marsRequest;
    }

    @Override
    public MarsRequest visitCoverageExpressionShorthandTrimLabel(CoverageExpressionShorthandTrimLabelContext ctx) {
        /*for (int i = 0; i < ctx.getChildCount(); i ++) {
            System.out.println(ctx.getChild(i).getText());
        }*/
        return visitChildren(ctx);
    }


    @Override public MarsRequest visitDimensionIntervalListLabel(DimensionIntervalListLabelContext ctx) {
        /*for (int i = 0; i < ctx.getChildCount(); i ++) {
                System.out.println(ctx.getChild(i).getText());
        }*/
        this.coordinatesAggregator = new CoordinatesAggregator();
        visitChildren(ctx);
        this.marsRequest.setArea(this.coordinatesAggregator.buildMarsArea());
        return this.marsRequest;
    }

    @Override
    public MarsRequest visitTrimDimensionIntervalElementLabel(TrimDimensionIntervalElementLabelContext ctx) {
        /*for (int i = 0; i < ctx.getChildCount(); i ++) {
            System.out.println(ctx.getChild(i).getText());
        }*/
        System.out.println(ctx.axisName().getText());
        if (WCPSEvalVisitor.LATITUDE_AXIS.equals(ctx.axisName().getText())) {
            ctx.coverageExpression().forEach(lat -> this.coordinatesAggregator.addLatitude(lat.getText()));
        } else if (WCPSEvalVisitor.LONGITUDE_AXIS.equals(ctx.axisName().getText())) {
            ctx.coverageExpression().forEach(lon -> this.coordinatesAggregator.addLongitude(lon.getText()));
        } else if (WCPSEvalVisitor.DATE_TIME_AXIS.equals(ctx.axisName().getText())) {

        }
        visitChildren(ctx);
        return this.marsRequest;
    }

    @Override
    public MarsRequest visitSliceDimensionIntervalElementLabel(SliceDimensionIntervalElementLabelContext ctx) {
        for (int i = 0; i < ctx.getChildCount(); i ++) {
            System.out.println(ctx.getChild(i).getText());
        }
        return visitChildren(ctx);
    }

    /*@Override
    public MarsRequest visitXwcps(XwcpsContext ctx) {
        return visitChildren(ctx);
    }*/

    public static void main(String[] args) {
        System.out.println(Double.parseDouble("51.52") > Double.parseDouble("51.52"));
    }
}
