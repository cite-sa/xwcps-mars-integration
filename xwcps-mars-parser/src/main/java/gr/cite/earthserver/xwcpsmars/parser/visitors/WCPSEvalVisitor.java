package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WCPSEvalVisitor extends XWCPSParseTreeVisitor {
    private static final Logger logger = LoggerFactory.getLogger(WCPSEvalVisitor.class);

    @Override
    public String visitDescribeCoverageExpressionLabel(DescribeCoverageExpressionLabelContext ctx) {
        return null;
    }

    @Override
    public String visitWcpsQuery(WcpsQueryContext ctx) {
        return null;
    }

    @Override
    public String visitEncodedCoverageExpressionLabel(EncodedCoverageExpressionLabelContext ctx) {
        return null;
    }

    @Override
    public abstract String visitXwcps(XwcpsContext ctx);

}
