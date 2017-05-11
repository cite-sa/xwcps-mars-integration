package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.*;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XWCPSEvalVisitor extends WCPSEvalVisitor {
    private static final Logger logger = LoggerFactory.getLogger(XWCPSEvalVisitor.class);

    public XWCPSEvalVisitor() {

    }

    public String getCoverageId() {
        return super.getCoverageId();
    }

    @Override
    public MarsRequest visitXwcps(XwcpsContext ctx) {
        return visitChildren(ctx);
    }

    /*@Override
    public MarsRequest visitXpath(XpathContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitOrderByClause(OrderByClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitXpathForClause(XpathForClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitXpathClause(XpathClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitLetClause(LetClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitXmlElement(XmlElementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitOpenXmlElement(OpenXmlElementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitOpenXmlWithClose(OpenXmlWithCloseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitCloseXmlElement(CloseXmlElementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitQuated(QuatedContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitXmlClause(XmlClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitAttribute(AttributeContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitProcessingExpression(ProcessingExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitWrapResultClause(WrapResultClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitBooleanXpathClause(BooleanXpathClauseContext ctx) {
		return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitWhereClause(WhereClauseContext ctx) {
		return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitMixedClause(MixedClauseContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitMetadataExpression(MetadataExpressionContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitSpecificIdInServerLabel(SpecificIdInServerLabelContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitAllCoveragesInServerLabel(AllCoveragesInServerLabelContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitAllCoveragesLabel(AllCoveragesLabelContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitSpecificIdLabel(SpecificIdLabelContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public MarsRequest visitForClause(ForClauseContext ctx) {
        return visitChildren(ctx);
    }*/
}
