// Generated from gr/cite/earthserver/mars/xwcps/grammar/WCPS.g4 by ANTLR 4.6
package gr.cite.earthserver.mars.xwcps.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link WCPSParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface WCPSVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code WcpsQueryLabel}
	 * labeled alternative in {@link WCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWcpsQueryLabel(WCPSParser.WcpsQueryLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForClauseListLabel}
	 * labeled alternative in {@link WCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClauseListLabel(WCPSParser.ForClauseListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForClauseLabel}
	 * labeled alternative in {@link WCPSParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClauseLabel(WCPSParser.ForClauseLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhereClauseLabel}
	 * labeled alternative in {@link WCPSParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClauseLabel(WCPSParser.WhereClauseLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link WCPSParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClauseLabel(WCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageVariableNameLabel(WCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcessingExpression(WCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarValueCoverageExpressionLabel(WCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarExpression(WCPSParser.ScalarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanBinaryScalarLabel(WCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanReduceExpression(WCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryScalarLabel(WCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanStringComparisonScalar(WCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstantLabel(WCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanNumericalComparisonScalarLabel(WCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanUnaryOperator(WCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanConstant(WCPSParser.BooleanConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanOperator(WCPSParser.BooleanOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalComparissonOperator(WCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringOperator(WCPSParser.StringOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringScalarExpressionLabel(WCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#starExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarExpressionLabel(WCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanSwitchCaseCoverageExpression(WCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanSwitchCaseCombinedExpression(WCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalTrigonometricScalarExpressionLabel(WCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalNanNumberExpressionLabel(WCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalRealNumberExpressionLabel(WCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalCondenseExpressionLabel(WCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalUnaryScalarExpressionLabel(WCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalBinaryScalarExpressionLabel(WCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalComplexNumberConstant(WCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link WCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexNumberConstantLabel(WCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalOperator(WCPSParser.NumericalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericalUnaryOperation(WCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigonometricOperator(WCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetComponentExpression(WCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageIdentifierExpressionLabel(WCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageCrsSetExpressionLabel(WCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainExpressionLabel(WCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsDomainByDimensionExpressionLabel(WCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsDomainExpressionLabel(WCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImageCrsExpressionLabel(WCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeCoverageExpressionLabel(WCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDomainIntervals(WCPSParser.DomainIntervalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#format_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormat_name(WCPSParser.Format_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#extra_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtra_params(WCPSParser.Extra_paramsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEncodedCoverageExpressionLabel(WCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecodedCoverageExpressionLabel(WCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionTrigonometricLabel(WCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionConstantLabel(WCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCoverageLabel(WCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionShorthandSliceLabel(WCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScaleLabel(WCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionArithmeticLabel(WCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionOverlayLabel(WCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExponentialLabel(WCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionLogicLabel(WCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionComparissonLabel(WCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScaleByDomainIntervalsLabel(WCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionSliceLabel(WCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionRangeSubsettingLabel(WCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionUnaryBooleanLabel(WCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionDomainIntervalsLabel(WCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionShorthandTrimLabel(WCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionVariableNameLabel(WCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionScalarLabel(WCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionRangeConstructorLabel(WCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExtendByDomainIntervalsLabel(WCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionTrimCoverageLabel(WCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCastLabel(WCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionPowerLabel(WCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionConstructorLabel(WCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionCrsTransformLabel(WCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionDecodeLabel(WCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionExtendLabel(WCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionUnaryArithmeticLabel(WCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageExpressionSwitchCaseLabel(WCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageArithmeticOperator(WCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryArithmeticExpressionOperator(WCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryCoverageArithmeticExpressionLabel(WCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link WCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrigonometricExpressionLabel(WCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpressionOperator(WCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link WCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentialExpressionLabel(WCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPowerExpressionLabel(WCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotUnaryBooleanExpressionLabel(WCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitUnaryBooleanExpressionLabel(WCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#rangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeType(WCPSParser.RangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link WCPSParser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpressionLabel(WCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(WCPSParser.FieldNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeConstructorExpressionLabel(WCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeConstructorSwitchCaseExpressionLabel(WCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionPointListLabel(WCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionPointElementLabel(WCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionIntervalListLabel(WCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrimDimensionIntervalElementLabel(WCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSliceDimensionIntervalElementLabel(WCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link WCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrsTransformExpressionLabel(WCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionCrsListLabel(WCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDimensionCrsElementLabel(WCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldInterpolationList(WCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link WCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldInterpolationListElementLabel(WCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link WCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterpolationMethodLabel(WCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link WCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInterpolationTypeLabel(WCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullResistance(WCPSParser.NullResistanceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageConstructorExpressionLabel(WCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisIteratorDomainIntervalsLabel(WCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisIteratorLabel(WCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalExpressionLabel(WCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCoverageConstantExpressionLabel(WCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link WCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisSpecLabel(WCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondenseExpression(WCPSParser.CondenseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceBooleanExpressionOperator(WCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceNumericalExpressionOperator(WCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceBooleanExpressionLabel(WCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceNumericalExpressionLabel(WCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpression(WCPSParser.ReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondenseExpressionOperator(WCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralCondenseExpressionLabel(WCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseRangeConstructorExpressionLabel(WCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchCaseScalarValueExpressionLabel(WCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#crsName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCrsName(WCPSParser.CrsNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#axisName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAxisName(WCPSParser.AxisNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(WCPSParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link WCPSParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(WCPSParser.ConstantContext ctx);
}