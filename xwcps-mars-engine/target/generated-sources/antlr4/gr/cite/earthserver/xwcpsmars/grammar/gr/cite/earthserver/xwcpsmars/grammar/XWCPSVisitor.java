// Generated from gr/cite/earthserver/xwcpsmars/grammar/XWCPS.g4 by ANTLR 4.7
package gr.cite.earthserver.xwcpsmars.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XWCPSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XWCPSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xwcps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXwcps(XWCPSParser.XwcpsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xpath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXpath(XWCPSParser.XpathContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(XWCPSParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(XWCPSParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#letClauseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClauseExpression(XWCPSParser.LetClauseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xmlClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlClause(XWCPSParser.XmlClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xmlPayload}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlPayload(XWCPSParser.XmlPayloadContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#arithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpression(XWCPSParser.ArithmeticExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xmlClauseWithQuate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlClauseWithQuate(XWCPSParser.XmlClauseWithQuateContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#openXmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenXmlElement(XWCPSParser.OpenXmlElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#openXmlWithClose}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpenXmlWithClose(XWCPSParser.OpenXmlWithCloseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXmlElement(XWCPSParser.XmlElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(XWCPSParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#quated}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuated(XWCPSParser.QuatedContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#closeXmlElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloseXmlElement(XWCPSParser.CloseXmlElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xpathClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXpathClause(XWCPSParser.XpathClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#wrapResultClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrapResultClause(XWCPSParser.WrapResultClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#wrapResultSubElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrapResultSubElement(XWCPSParser.WrapResultSubElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xpathForClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXpathForClause(XWCPSParser.XpathForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xwcpsCoveragesClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXwcpsCoveragesClause(XWCPSParser.XwcpsCoveragesClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#mixedClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMixedClause(XWCPSParser.MixedClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#metadataExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetadataExpression(XWCPSParser.MetadataExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(XWCPSParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanXpathClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanXpathClause(XWCPSParser.BooleanXpathClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcessingExpression(XWCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWcpsQuery(XWCPSParser.WcpsQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClauseList(XWCPSParser.ForClauseListContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#xwcpsforClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXwcpsforClause(XWCPSParser.XwcpsforClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#endpointIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndpointIdentifier(XWCPSParser.EndpointIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specificIdInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificIdInServerLabel(XWCPSParser.SpecificIdInServerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allCoveragesInServerLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllCoveragesInServerLabel(XWCPSParser.AllCoveragesInServerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code allCoveragesLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllCoveragesLabel(XWCPSParser.AllCoveragesLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code specificIdLabel}
	 * labeled alternative in {@link XWCPSParser#extendedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecificIdLabel(XWCPSParser.SpecificIdLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(XWCPSParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link XWCPSParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClauseLabel(XWCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageVariableNameLabel(XWCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarValueCoverageExpressionLabel(XWCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarExpression(XWCPSParser.ScalarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanBinaryScalarLabel(XWCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanReduceExpression(XWCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryScalarLabel(XWCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanStringComparisonScalar(XWCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstantLabel(XWCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link XWCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanNumericalComparisonScalarLabel(XWCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryOperator(XWCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstant(XWCPSParser.BooleanConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOperator(XWCPSParser.BooleanOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalComparissonOperator(XWCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringOperator(XWCPSParser.StringOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringScalarExpressionLabel(XWCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#starExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarExpressionLabel(XWCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanSwitchCaseCoverageExpression(XWCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanSwitchCaseCombinedExpression(XWCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalTrigonometricScalarExpressionLabel(XWCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalNanNumberExpressionLabel(XWCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalRealNumberExpressionLabel(XWCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalCondenseExpressionLabel(XWCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalUnaryScalarExpressionLabel(XWCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalBinaryScalarExpressionLabel(XWCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link XWCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalComplexNumberConstant(XWCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link XWCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexNumberConstantLabel(XWCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalOperator(XWCPSParser.NumericalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalUnaryOperation(XWCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigonometricOperator(XWCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetComponentExpression(XWCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageIdentifierExpressionLabel(XWCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageCrsSetExpressionLabel(XWCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainExpressionLabel(XWCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsDomainByDimensionExpressionLabel(XWCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsDomainExpressionLabel(XWCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsExpressionLabel(XWCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeCoverageExpressionLabel(XWCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainIntervals(XWCPSParser.DomainIntervalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#format_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormat_name(XWCPSParser.Format_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#extra_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtra_params(XWCPSParser.Extra_paramsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncodedCoverageExpressionLabel(XWCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecodedCoverageExpressionLabel(XWCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionTrigonometricLabel(XWCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionConstantLabel(XWCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCoverageLabel(XWCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionShorthandSliceLabel(XWCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScaleLabel(XWCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionArithmeticLabel(XWCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionOverlayLabel(XWCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExponentialLabel(XWCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionLogicLabel(XWCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionComparissonLabel(XWCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScaleByDomainIntervalsLabel(XWCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionSliceLabel(XWCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionRangeSubsettingLabel(XWCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionUnaryBooleanLabel(XWCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionDomainIntervalsLabel(XWCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionShorthandTrimLabel(XWCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionVariableNameLabel(XWCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScalarLabel(XWCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionRangeConstructorLabel(XWCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExtendByDomainIntervalsLabel(XWCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionTrimCoverageLabel(XWCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCastLabel(XWCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionPowerLabel(XWCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionConstructorLabel(XWCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCrsTransformLabel(XWCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionDecodeLabel(XWCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExtendLabel(XWCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionUnaryArithmeticLabel(XWCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link XWCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionSwitchCaseLabel(XWCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageArithmeticOperator(XWCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryArithmeticExpressionOperator(XWCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryCoverageArithmeticExpressionLabel(XWCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigonometricExpressionLabel(XWCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpressionOperator(XWCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpressionLabel(XWCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPowerExpressionLabel(XWCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotUnaryBooleanExpressionLabel(XWCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitUnaryBooleanExpressionLabel(XWCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#rangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeType(XWCPSParser.RangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpressionLabel(XWCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(XWCPSParser.FieldNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeConstructorExpressionLabel(XWCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeConstructorSwitchCaseExpressionLabel(XWCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionPointListLabel(XWCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionPointElementLabel(XWCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionIntervalListLabel(XWCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrimDimensionIntervalElementLabel(XWCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceDimensionIntervalElementLabel(XWCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrsTransformExpressionLabel(XWCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionCrsListLabel(XWCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link XWCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionCrsElementLabel(XWCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldInterpolationList(XWCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link XWCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldInterpolationListElementLabel(XWCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterpolationMethodLabel(XWCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link XWCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterpolationTypeLabel(XWCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullResistance(XWCPSParser.NullResistanceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageConstructorExpressionLabel(XWCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisIteratorDomainIntervalsLabel(XWCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link XWCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisIteratorLabel(XWCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpressionLabel(XWCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageConstantExpressionLabel(XWCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link XWCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisSpecLabel(XWCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondenseExpression(XWCPSParser.CondenseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceBooleanExpressionOperator(XWCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceNumericalExpressionOperator(XWCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceBooleanExpressionLabel(XWCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceNumericalExpressionLabel(XWCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpression(XWCPSParser.ReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondenseExpressionOperator(XWCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralCondenseExpressionLabel(XWCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseRangeConstructorExpressionLabel(XWCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link XWCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseScalarValueExpressionLabel(XWCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#crsName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrsName(XWCPSParser.CrsNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#axisName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisName(XWCPSParser.AxisNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(XWCPSParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(XWCPSParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#main}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMain(XWCPSParser.MainContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#locationPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocationPath(XWCPSParser.LocationPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#absoluteLocationPathNoroot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbsoluteLocationPathNoroot(XWCPSParser.AbsoluteLocationPathNorootContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#relativeLocationPath}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelativeLocationPath(XWCPSParser.RelativeLocationPathContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(XWCPSParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#axisSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisSpecifier(XWCPSParser.AxisSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#nodeTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeTest(XWCPSParser.NodeTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#predicate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPredicate(XWCPSParser.PredicateContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#abbreviatedStep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbbreviatedStep(XWCPSParser.AbbreviatedStepContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(XWCPSParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#primaryExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpr(XWCPSParser.PrimaryExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(XWCPSParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#unionExprNoRoot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionExprNoRoot(XWCPSParser.UnionExprNoRootContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#pathExprNoRoot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathExprNoRoot(XWCPSParser.PathExprNoRootContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#filterExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpr(XWCPSParser.FilterExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#orExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(XWCPSParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#andExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(XWCPSParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#equalityExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpr(XWCPSParser.EqualityExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#relationalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpr(XWCPSParser.RelationalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#additiveExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpr(XWCPSParser.AdditiveExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#multiplicativeExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpr(XWCPSParser.MultiplicativeExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#unaryExprNoRoot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExprNoRoot(XWCPSParser.UnaryExprNoRootContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#qName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQName(XWCPSParser.QNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(XWCPSParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#variableReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableReference(XWCPSParser.VariableReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#nameTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameTest(XWCPSParser.NameTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#nCName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNCName(XWCPSParser.NCNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XWCPSParser#wcpsHotWords}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWcpsHotWords(XWCPSParser.WcpsHotWordsContext ctx);
}