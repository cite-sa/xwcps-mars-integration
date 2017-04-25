package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XWCPSEvalVisitor extends WCPSEvalVisitor {
    private static final Logger logger = LoggerFactory.getLogger(XWCPSEvalVisitor.class);

    public XWCPSEvalVisitor() {

    }

    @Override
    public String visitXwcps(XwcpsContext ctx) {
        return null;
    }

    @Override
    public String visitXpath(XpathContext ctx) {
        return null;
    }

    @Override
    public String visitOrderByClause(OrderByClauseContext ctx) {
        return null;
    }

    @Override
    public String visitXpathForClause(XpathForClauseContext ctx) {
        return null;
    }

    @Override
    public String visitXpathClause(XpathClauseContext ctx) {
        return null;
    }

    @Override
    public String visitLetClause(LetClauseContext ctx) {
        return null;
    }

    @Override
    public String visitXmlElement(XmlElementContext ctx) {
        return null;
    }

    @Override
    public String visitOpenXmlElement(OpenXmlElementContext ctx) {
        return null;
    }

    @Override
    public String visitOpenXmlWithClose(OpenXmlWithCloseContext ctx) {
        return null;
    }

    @Override
    public String visitCloseXmlElement(CloseXmlElementContext ctx) {
        return null;
    }

    @Override
    public String visitQuated(QuatedContext ctx) {
        return null;
    }

    @Override
    public String visitXmlClause(XmlClauseContext ctx) {
        return null;
    }

    @Override
    public String visitAttribute(AttributeContext ctx) {
        return null;
    }

    @Override
    public String visitProcessingExpression(ProcessingExpressionContext ctx) {
        return null;
    }

    @Override
    public String visitWrapResultClause(WrapResultClauseContext ctx) {
        return null;
    }

    /*@Override
    public String visitIdentifier(IdentifierContext ctx) {
        return null;
    }*/

    @Override
    public String visitBooleanXpathClause(BooleanXpathClauseContext ctx) {
		return null;
    }

    @Override
    public String visitWhereClause(WhereClauseContext ctx) {
		return null;
    }

    @Override
    public String visitMixedClause(MixedClauseContext ctx) {
        return null;
    }

    @Override
    public String visitMetadataExpression(MetadataExpressionContext ctx) {
        return null;
    }

    @Override
    public String visitSpecificIdInServerLabel(SpecificIdInServerLabelContext ctx) {
        return null;
    }

    @Override
    public String visitAllCoveragesInServerLabel(AllCoveragesInServerLabelContext ctx) {
        return null;
    }

    @Override
    public String visitAllCoveragesLabel(AllCoveragesLabelContext ctx) {
        return null;
    }

    @Override
    public String visitSpecificIdLabel(SpecificIdLabelContext ctx) {
        return null;
    }

    @Override
    public String visitForClause(ForClauseContext ctx) {
        return null;
    }
}
