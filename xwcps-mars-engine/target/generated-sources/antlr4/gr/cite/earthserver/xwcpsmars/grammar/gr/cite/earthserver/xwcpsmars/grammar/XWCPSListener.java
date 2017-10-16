// Generated from gr/cite/earthserver/xwcpsmars/grammar/XWCPS.g4 by ANTLR 4.7
package gr.cite.earthserver.xwcpsmars.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XWCPSParser}.
 */
public interface XWCPSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xwcps}.
	 * @param ctx the parse tree
	 */
	void enterXwcps(XWCPSParser.XwcpsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xwcps}.
	 * @param ctx the parse tree
	 */
	void exitXwcps(XWCPSParser.XwcpsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xpath}.
	 * @param ctx the parse tree
	 */
	void enterXpath(XWCPSParser.XpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xpath}.
	 * @param ctx the parse tree
	 */
	void exitXpath(XWCPSParser.XpathContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(XWCPSParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(XWCPSParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(XWCPSParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(XWCPSParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#letClauseExpression}.
	 * @param ctx the parse tree
	 */
	void enterLetClauseExpression(XWCPSParser.LetClauseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#letClauseExpression}.
	 * @param ctx the parse tree
	 */
	void exitLetClauseExpression(XWCPSParser.LetClauseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xmlClause}.
	 * @param ctx the parse tree
	 */
	void enterXmlClause(XWCPSParser.XmlClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xmlClause}.
	 * @param ctx the parse tree
	 */
	void exitXmlClause(XWCPSParser.XmlClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xmlPayload}.
	 * @param ctx the parse tree
	 */
	void enterXmlPayload(XWCPSParser.XmlPayloadContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xmlPayload}.
	 * @param ctx the parse tree
	 */
	void exitXmlPayload(XWCPSParser.XmlPayloadContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpression(XWCPSParser.ArithmeticExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpression(XWCPSParser.ArithmeticExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xmlClauseWithQuate}.
	 * @param ctx the parse tree
	 */
	void enterXmlClauseWithQuate(XWCPSParser.XmlClauseWithQuateContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xmlClauseWithQuate}.
	 * @param ctx the parse tree
	 */
	void exitXmlClauseWithQuate(XWCPSParser.XmlClauseWithQuateContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#openXmlElement}.
	 * @param ctx the parse tree
	 */
	void enterOpenXmlElement(XWCPSParser.OpenXmlElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#openXmlElement}.
	 * @param ctx the parse tree
	 */
	void exitOpenXmlElement(XWCPSParser.OpenXmlElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#openXmlWithClose}.
	 * @param ctx the parse tree
	 */
	void enterOpenXmlWithClose(XWCPSParser.OpenXmlWithCloseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#openXmlWithClose}.
	 * @param ctx the parse tree
	 */
	void exitOpenXmlWithClose(XWCPSParser.OpenXmlWithCloseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xmlElement}.
	 * @param ctx the parse tree
	 */
	void enterXmlElement(XWCPSParser.XmlElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xmlElement}.
	 * @param ctx the parse tree
	 */
	void exitXmlElement(XWCPSParser.XmlElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XWCPSParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XWCPSParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#quated}.
	 * @param ctx the parse tree
	 */
	void enterQuated(XWCPSParser.QuatedContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#quated}.
	 * @param ctx the parse tree
	 */
	void exitQuated(XWCPSParser.QuatedContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#closeXmlElement}.
	 * @param ctx the parse tree
	 */
	void enterCloseXmlElement(XWCPSParser.CloseXmlElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#closeXmlElement}.
	 * @param ctx the parse tree
	 */
	void exitCloseXmlElement(XWCPSParser.CloseXmlElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xpathClause}.
	 * @param ctx the parse tree
	 */
	void enterXpathClause(XWCPSParser.XpathClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xpathClause}.
	 * @param ctx the parse tree
	 */
	void exitXpathClause(XWCPSParser.XpathClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#wrapResultClause}.
	 * @param ctx the parse tree
	 */
	void enterWrapResultClause(XWCPSParser.WrapResultClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#wrapResultClause}.
	 * @param ctx the parse tree
	 */
	void exitWrapResultClause(XWCPSParser.WrapResultClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#wrapResultSubElement}.
	 * @param ctx the parse tree
	 */
	void enterWrapResultSubElement(XWCPSParser.WrapResultSubElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#wrapResultSubElement}.
	 * @param ctx the parse tree
	 */
	void exitWrapResultSubElement(XWCPSParser.WrapResultSubElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xpathForClause}.
	 * @param ctx the parse tree
	 */
	void enterXpathForClause(XWCPSParser.XpathForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xpathForClause}.
	 * @param ctx the parse tree
	 */
	void exitXpathForClause(XWCPSParser.XpathForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xwcpsCoveragesClause}.
	 * @param ctx the parse tree
	 */
	void enterXwcpsCoveragesClause(XWCPSParser.XwcpsCoveragesClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xwcpsCoveragesClause}.
	 * @param ctx the parse tree
	 */
	void exitXwcpsCoveragesClause(XWCPSParser.XwcpsCoveragesClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#mixedClause}.
	 * @param ctx the parse tree
	 */
	void enterMixedClause(XWCPSParser.MixedClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#mixedClause}.
	 * @param ctx the parse tree
	 */
	void exitMixedClause(XWCPSParser.MixedClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#metadataExpression}.
	 * @param ctx the parse tree
	 */
	void enterMetadataExpression(XWCPSParser.MetadataExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#metadataExpression}.
	 * @param ctx the parse tree
	 */
	void exitMetadataExpression(XWCPSParser.MetadataExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(XWCPSParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(XWCPSParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanXpathClause}.
	 * @param ctx the parse tree
	 */
	void enterBooleanXpathClause(XWCPSParser.BooleanXpathClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanXpathClause}.
	 * @param ctx the parse tree
	 */
	void exitBooleanXpathClause(XWCPSParser.BooleanXpathClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 */
	void enterProcessingExpression(XWCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 */
	void exitProcessingExpression(XWCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 */
	void enterWcpsQuery(XWCPSParser.WcpsQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 */
	void exitWcpsQuery(XWCPSParser.WcpsQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 */
	void enterForClauseList(XWCPSParser.ForClauseListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 */
	void exitForClauseList(XWCPSParser.ForClauseListContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#xwcpsforClause}.
	 * @param ctx the parse tree
	 */
	void enterXwcpsforClause(XWCPSParser.XwcpsforClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#xwcpsforClause}.
	 * @param ctx the parse tree
	 */
	void exitXwcpsforClause(XWCPSParser.XwcpsforClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#endpointIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterEndpointIdentifier(XWCPSParser.EndpointIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#endpointIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitEndpointIdentifier(XWCPSParser.EndpointIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specificIdInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterSpecificIdInServerLabel(XWCPSParser.SpecificIdInServerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specificIdInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitSpecificIdInServerLabel(XWCPSParser.SpecificIdInServerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allCoveragesInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterAllCoveragesInServerLabel(XWCPSParser.AllCoveragesInServerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allCoveragesInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitAllCoveragesInServerLabel(XWCPSParser.AllCoveragesInServerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code allCoveragesLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterAllCoveragesLabel(XWCPSParser.AllCoveragesLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code allCoveragesLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitAllCoveragesLabel(XWCPSParser.AllCoveragesLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code specificIdLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterSpecificIdLabel(XWCPSParser.SpecificIdLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code specificIdLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitSpecificIdLabel(XWCPSParser.SpecificIdLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(XWCPSParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(XWCPSParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link XWCPSParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClauseLabel(XWCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link XWCPSParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClauseLabel(XWCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 */
	void enterCoverageVariableNameLabel(XWCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 */
	void exitCoverageVariableNameLabel(XWCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterScalarValueCoverageExpressionLabel(XWCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitScalarValueCoverageExpressionLabel(XWCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterScalarExpression(XWCPSParser.ScalarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitScalarExpression(XWCPSParser.ScalarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanBinaryScalarLabel(XWCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanBinaryScalarLabel(XWCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanReduceExpression(XWCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanReduceExpression(XWCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanUnaryScalarLabel(XWCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanUnaryScalarLabel(XWCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanStringComparisonScalar(XWCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanStringComparisonScalar(XWCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanConstantLabel(XWCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanConstantLabel(XWCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanNumericalComparisonScalarLabel(XWCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanNumericalComparisonScalarLabel(XWCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanUnaryOperator(XWCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanUnaryOperator(XWCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 */
	void enterBooleanConstant(XWCPSParser.BooleanConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 */
	void exitBooleanConstant(XWCPSParser.BooleanConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(XWCPSParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(XWCPSParser.BooleanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumericalComparissonOperator(XWCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumericalComparissonOperator(XWCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 */
	void enterStringOperator(XWCPSParser.StringOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 */
	void exitStringOperator(XWCPSParser.StringOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringScalarExpressionLabel(XWCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringScalarExpressionLabel(XWCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#starExpression}.
	 * @param ctx the parse tree
	 */
	void enterStarExpressionLabel(XWCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#starExpression}.
	 * @param ctx the parse tree
	 */
	void exitStarExpressionLabel(XWCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanSwitchCaseCoverageExpression(XWCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanSwitchCaseCoverageExpression(XWCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanSwitchCaseCombinedExpression(XWCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanSwitchCaseCombinedExpression(XWCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalTrigonometricScalarExpressionLabel(XWCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalTrigonometricScalarExpressionLabel(XWCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalNanNumberExpressionLabel(XWCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalNanNumberExpressionLabel(XWCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalRealNumberExpressionLabel(XWCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalRealNumberExpressionLabel(XWCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalCondenseExpressionLabel(XWCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalCondenseExpressionLabel(XWCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalUnaryScalarExpressionLabel(XWCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalUnaryScalarExpressionLabel(XWCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalBinaryScalarExpressionLabel(XWCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalBinaryScalarExpressionLabel(XWCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalComplexNumberConstant(XWCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalComplexNumberConstant(XWCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link XWCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 */
	void enterComplexNumberConstantLabel(XWCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link XWCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 */
	void exitComplexNumberConstantLabel(XWCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumericalOperator(XWCPSParser.NumericalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumericalOperator(XWCPSParser.NumericalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterNumericalUnaryOperation(XWCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitNumericalUnaryOperation(XWCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 */
	void enterTrigonometricOperator(XWCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 */
	void exitTrigonometricOperator(XWCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 */
	void enterGetComponentExpression(XWCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 */
	void exitGetComponentExpression(XWCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageIdentifierExpressionLabel(XWCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageIdentifierExpressionLabel(XWCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageCrsSetExpressionLabel(XWCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageCrsSetExpressionLabel(XWCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 */
	void enterDomainExpressionLabel(XWCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 */
	void exitDomainExpressionLabel(XWCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsDomainByDimensionExpressionLabel(XWCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsDomainByDimensionExpressionLabel(XWCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsDomainExpressionLabel(XWCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsDomainExpressionLabel(XWCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsExpressionLabel(XWCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsExpressionLabel(XWCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterDescribeCoverageExpressionLabel(XWCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitDescribeCoverageExpressionLabel(XWCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 */
	void enterDomainIntervals(XWCPSParser.DomainIntervalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 */
	void exitDomainIntervals(XWCPSParser.DomainIntervalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#format_name}.
	 * @param ctx the parse tree
	 */
	void enterFormat_name(XWCPSParser.Format_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#format_name}.
	 * @param ctx the parse tree
	 */
	void exitFormat_name(XWCPSParser.Format_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#extra_params}.
	 * @param ctx the parse tree
	 */
	void enterExtra_params(XWCPSParser.Extra_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#extra_params}.
	 * @param ctx the parse tree
	 */
	void exitExtra_params(XWCPSParser.Extra_paramsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterEncodedCoverageExpressionLabel(XWCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitEncodedCoverageExpressionLabel(XWCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterDecodedCoverageExpressionLabel(XWCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitDecodedCoverageExpressionLabel(XWCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionTrigonometricLabel(XWCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionTrigonometricLabel(XWCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionConstantLabel(XWCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionConstantLabel(XWCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCoverageLabel(XWCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCoverageLabel(XWCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionShorthandSliceLabel(XWCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionShorthandSliceLabel(XWCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScaleLabel(XWCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScaleLabel(XWCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionArithmeticLabel(XWCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionArithmeticLabel(XWCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionOverlayLabel(XWCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionOverlayLabel(XWCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExponentialLabel(XWCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExponentialLabel(XWCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionLogicLabel(XWCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionLogicLabel(XWCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionComparissonLabel(XWCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionComparissonLabel(XWCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScaleByDomainIntervalsLabel(XWCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScaleByDomainIntervalsLabel(XWCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionSliceLabel(XWCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionSliceLabel(XWCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionRangeSubsettingLabel(XWCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionRangeSubsettingLabel(XWCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionUnaryBooleanLabel(XWCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionUnaryBooleanLabel(XWCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionDomainIntervalsLabel(XWCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionDomainIntervalsLabel(XWCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionShorthandTrimLabel(XWCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionShorthandTrimLabel(XWCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionVariableNameLabel(XWCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionVariableNameLabel(XWCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScalarLabel(XWCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScalarLabel(XWCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionRangeConstructorLabel(XWCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionRangeConstructorLabel(XWCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExtendByDomainIntervalsLabel(XWCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExtendByDomainIntervalsLabel(XWCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionTrimCoverageLabel(XWCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionTrimCoverageLabel(XWCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCastLabel(XWCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCastLabel(XWCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionPowerLabel(XWCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionPowerLabel(XWCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionConstructorLabel(XWCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionConstructorLabel(XWCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCrsTransformLabel(XWCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCrsTransformLabel(XWCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionDecodeLabel(XWCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionDecodeLabel(XWCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExtendLabel(XWCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExtendLabel(XWCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionUnaryArithmeticLabel(XWCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionUnaryArithmeticLabel(XWCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionSwitchCaseLabel(XWCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionSwitchCaseLabel(XWCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterCoverageArithmeticOperator(XWCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitCoverageArithmeticOperator(XWCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryArithmeticExpressionOperator(XWCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryArithmeticExpressionOperator(XWCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryCoverageArithmeticExpressionLabel(XWCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryCoverageArithmeticExpressionLabel(XWCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrigonometricExpressionLabel(XWCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrigonometricExpressionLabel(XWCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterExponentialExpressionOperator(XWCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitExponentialExpressionOperator(XWCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 */
	void enterExponentialExpressionLabel(XWCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 */
	void exitExponentialExpressionLabel(XWCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPowerExpressionLabel(XWCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPowerExpressionLabel(XWCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotUnaryBooleanExpressionLabel(XWCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotUnaryBooleanExpressionLabel(XWCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitUnaryBooleanExpressionLabel(XWCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitUnaryBooleanExpressionLabel(XWCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#rangeType}.
	 * @param ctx the parse tree
	 */
	void enterRangeType(XWCPSParser.RangeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#rangeType}.
	 * @param ctx the parse tree
	 */
	void exitRangeType(XWCPSParser.RangeTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpressionLabel(XWCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpressionLabel(XWCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(XWCPSParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(XWCPSParser.FieldNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void enterRangeConstructorExpressionLabel(XWCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void exitRangeConstructorExpressionLabel(XWCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterRangeConstructorSwitchCaseExpressionLabel(XWCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitRangeConstructorSwitchCaseExpressionLabel(XWCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionPointListLabel(XWCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionPointListLabel(XWCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 */
	void enterDimensionPointElementLabel(XWCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 */
	void exitDimensionPointElementLabel(XWCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionIntervalListLabel(XWCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionIntervalListLabel(XWCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void enterTrimDimensionIntervalElementLabel(XWCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void exitTrimDimensionIntervalElementLabel(XWCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void enterSliceDimensionIntervalElementLabel(XWCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void exitSliceDimensionIntervalElementLabel(XWCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 */
	void enterCrsTransformExpressionLabel(XWCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 */
	void exitCrsTransformExpressionLabel(XWCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionCrsListLabel(XWCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionCrsListLabel(XWCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 */
	void enterDimensionCrsElementLabel(XWCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 */
	void exitDimensionCrsElementLabel(XWCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 */
	void enterFieldInterpolationList(XWCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 */
	void exitFieldInterpolationList(XWCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link XWCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 */
	void enterFieldInterpolationListElementLabel(XWCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link XWCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 */
	void exitFieldInterpolationListElementLabel(XWCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 */
	void enterInterpolationMethodLabel(XWCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 */
	void exitInterpolationMethodLabel(XWCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 */
	void enterInterpolationTypeLabel(XWCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 */
	void exitInterpolationTypeLabel(XWCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 */
	void enterNullResistance(XWCPSParser.NullResistanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 */
	void exitNullResistance(XWCPSParser.NullResistanceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageConstructorExpressionLabel(XWCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageConstructorExpressionLabel(XWCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void enterAxisIteratorDomainIntervalsLabel(XWCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void exitAxisIteratorDomainIntervalsLabel(XWCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void enterAxisIteratorLabel(XWCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void exitAxisIteratorLabel(XWCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpressionLabel(XWCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpressionLabel(XWCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageConstantExpressionLabel(XWCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageConstantExpressionLabel(XWCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link XWCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 */
	void enterAxisSpecLabel(XWCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link XWCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 */
	void exitAxisSpecLabel(XWCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCondenseExpression(XWCPSParser.CondenseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCondenseExpression(XWCPSParser.CondenseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterReduceBooleanExpressionOperator(XWCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitReduceBooleanExpressionOperator(XWCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterReduceNumericalExpressionOperator(XWCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitReduceNumericalExpressionOperator(XWCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceBooleanExpressionLabel(XWCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceBooleanExpressionLabel(XWCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceNumericalExpressionLabel(XWCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceNumericalExpressionLabel(XWCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpression(XWCPSParser.ReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpression(XWCPSParser.ReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterCondenseExpressionOperator(XWCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitCondenseExpressionOperator(XWCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 */
	void enterGeneralCondenseExpressionLabel(XWCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 */
	void exitGeneralCondenseExpressionLabel(XWCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCaseRangeConstructorExpressionLabel(XWCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCaseRangeConstructorExpressionLabel(XWCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCaseScalarValueExpressionLabel(XWCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCaseScalarValueExpressionLabel(XWCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#crsName}.
	 * @param ctx the parse tree
	 */
	void enterCrsName(XWCPSParser.CrsNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#crsName}.
	 * @param ctx the parse tree
	 */
	void exitCrsName(XWCPSParser.CrsNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#axisName}.
	 * @param ctx the parse tree
	 */
	void enterAxisName(XWCPSParser.AxisNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#axisName}.
	 * @param ctx the parse tree
	 */
	void exitAxisName(XWCPSParser.AxisNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(XWCPSParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(XWCPSParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(XWCPSParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(XWCPSParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#main}.
	 * @param ctx the parse tree
	 */
	void enterMain(XWCPSParser.MainContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#main}.
	 * @param ctx the parse tree
	 */
	void exitMain(XWCPSParser.MainContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#locationPath}.
	 * @param ctx the parse tree
	 */
	void enterLocationPath(XWCPSParser.LocationPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#locationPath}.
	 * @param ctx the parse tree
	 */
	void exitLocationPath(XWCPSParser.LocationPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#absoluteLocationPathNoroot}.
	 * @param ctx the parse tree
	 */
	void enterAbsoluteLocationPathNoroot(XWCPSParser.AbsoluteLocationPathNorootContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#absoluteLocationPathNoroot}.
	 * @param ctx the parse tree
	 */
	void exitAbsoluteLocationPathNoroot(XWCPSParser.AbsoluteLocationPathNorootContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#relativeLocationPath}.
	 * @param ctx the parse tree
	 */
	void enterRelativeLocationPath(XWCPSParser.RelativeLocationPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#relativeLocationPath}.
	 * @param ctx the parse tree
	 */
	void exitRelativeLocationPath(XWCPSParser.RelativeLocationPathContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(XWCPSParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(XWCPSParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#axisSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterAxisSpecifier(XWCPSParser.AxisSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#axisSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitAxisSpecifier(XWCPSParser.AxisSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void enterNodeTest(XWCPSParser.NodeTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#nodeTest}.
	 * @param ctx the parse tree
	 */
	void exitNodeTest(XWCPSParser.NodeTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicate(XWCPSParser.PredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicate(XWCPSParser.PredicateContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#abbreviatedStep}.
	 * @param ctx the parse tree
	 */
	void enterAbbreviatedStep(XWCPSParser.AbbreviatedStepContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#abbreviatedStep}.
	 * @param ctx the parse tree
	 */
	void exitAbbreviatedStep(XWCPSParser.AbbreviatedStepContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(XWCPSParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(XWCPSParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpr(XWCPSParser.PrimaryExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#primaryExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpr(XWCPSParser.PrimaryExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(XWCPSParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(XWCPSParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#unionExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void enterUnionExprNoRoot(XWCPSParser.UnionExprNoRootContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#unionExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void exitUnionExprNoRoot(XWCPSParser.UnionExprNoRootContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#pathExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void enterPathExprNoRoot(XWCPSParser.PathExprNoRootContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#pathExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void exitPathExprNoRoot(XWCPSParser.PathExprNoRootContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpr(XWCPSParser.FilterExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#filterExpr}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpr(XWCPSParser.FilterExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(XWCPSParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#orExpr}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(XWCPSParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(XWCPSParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#andExpr}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(XWCPSParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpr(XWCPSParser.EqualityExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#equalityExpr}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpr(XWCPSParser.EqualityExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpr(XWCPSParser.RelationalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#relationalExpr}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpr(XWCPSParser.RelationalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpr(XWCPSParser.AdditiveExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#additiveExpr}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpr(XWCPSParser.AdditiveExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpr(XWCPSParser.MultiplicativeExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpr(XWCPSParser.MultiplicativeExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#unaryExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExprNoRoot(XWCPSParser.UnaryExprNoRootContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#unaryExprNoRoot}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExprNoRoot(XWCPSParser.UnaryExprNoRootContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#qName}.
	 * @param ctx the parse tree
	 */
	void enterQName(XWCPSParser.QNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#qName}.
	 * @param ctx the parse tree
	 */
	void exitQName(XWCPSParser.QNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(XWCPSParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(XWCPSParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void enterVariableReference(XWCPSParser.VariableReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#variableReference}.
	 * @param ctx the parse tree
	 */
	void exitVariableReference(XWCPSParser.VariableReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void enterNameTest(XWCPSParser.NameTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#nameTest}.
	 * @param ctx the parse tree
	 */
	void exitNameTest(XWCPSParser.NameTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#nCName}.
	 * @param ctx the parse tree
	 */
	void enterNCName(XWCPSParser.NCNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#nCName}.
	 * @param ctx the parse tree
	 */
	void exitNCName(XWCPSParser.NCNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XWCPSParser#wcpsHotWords}.
	 * @param ctx the parse tree
	 */
	void enterWcpsHotWords(XWCPSParser.WcpsHotWordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link XWCPSParser#wcpsHotWords}.
	 * @param ctx the parse tree
	 */
	void exitWcpsHotWords(XWCPSParser.WcpsHotWordsContext ctx);
}