// Generated from gr/cite/earthserver/xwcpsmars/grammar/WCPS.g4 by ANTLR 4.7
package gr.cite.earthserver.xwcpsmars.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WCPSParser}.
 */
public interface WCPSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code WcpsQueryLabel}
	 * labeled alternative in {@link WCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 */
	void enterWcpsQueryLabel(WCPSParser.WcpsQueryLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WcpsQueryLabel}
	 * labeled alternative in {@link WCPSParser#wcpsQuery}.
	 * @param ctx the parse tree
	 */
	void exitWcpsQueryLabel(WCPSParser.WcpsQueryLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForClauseListLabel}
	 * labeled alternative in {@link WCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 */
	void enterForClauseListLabel(WCPSParser.ForClauseListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForClauseListLabel}
	 * labeled alternative in {@link WCPSParser#forClauseList}.
	 * @param ctx the parse tree
	 */
	void exitForClauseListLabel(WCPSParser.ForClauseListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForClauseLabel}
	 * labeled alternative in {@link WCPSParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClauseLabel(WCPSParser.ForClauseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForClauseLabel}
	 * labeled alternative in {@link WCPSParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClauseLabel(WCPSParser.ForClauseLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhereClauseLabel}
	 * labeled alternative in {@link WCPSParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClauseLabel(WCPSParser.WhereClauseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhereClauseLabel}
	 * labeled alternative in {@link WCPSParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClauseLabel(WCPSParser.WhereClauseLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link WCPSParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClauseLabel(WCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReturnClauseLabel}
	 * labeled alternative in {@link WCPSParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClauseLabel(WCPSParser.ReturnClauseLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 */
	void enterCoverageVariableNameLabel(WCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageVariableName}.
	 * @param ctx the parse tree
	 */
	void exitCoverageVariableNameLabel(WCPSParser.CoverageVariableNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 */
	void enterProcessingExpression(WCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#processingExpression}.
	 * @param ctx the parse tree
	 */
	void exitProcessingExpression(WCPSParser.ProcessingExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterScalarValueCoverageExpressionLabel(WCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarValueCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#scalarValueCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitScalarValueCoverageExpressionLabel(WCPSParser.ScalarValueCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterScalarExpression(WCPSParser.ScalarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#scalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitScalarExpression(WCPSParser.ScalarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanBinaryScalarLabel(WCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanBinaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanBinaryScalarLabel(WCPSParser.BooleanBinaryScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanReduceExpression(WCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanReduceExpression}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanReduceExpression(WCPSParser.BooleanReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanUnaryScalarLabel(WCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanUnaryScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanUnaryScalarLabel(WCPSParser.BooleanUnaryScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanStringComparisonScalar(WCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanStringComparisonScalar}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanStringComparisonScalar(WCPSParser.BooleanStringComparisonScalarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanConstantLabel(WCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanConstantLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanConstantLabel(WCPSParser.BooleanConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanNumericalComparisonScalarLabel(WCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanNumericalComparisonScalarLabel}
	 * labeled alternative in {@link WCPSParser#booleanScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanNumericalComparisonScalarLabel(WCPSParser.BooleanNumericalComparisonScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanUnaryOperator(WCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#booleanUnaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanUnaryOperator(WCPSParser.BooleanUnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 */
	void enterBooleanConstant(WCPSParser.BooleanConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#booleanConstant}.
	 * @param ctx the parse tree
	 */
	void exitBooleanConstant(WCPSParser.BooleanConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOperator(WCPSParser.BooleanOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#booleanOperator}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOperator(WCPSParser.BooleanOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumericalComparissonOperator(WCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#numericalComparissonOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumericalComparissonOperator(WCPSParser.NumericalComparissonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 */
	void enterStringOperator(WCPSParser.StringOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#stringOperator}.
	 * @param ctx the parse tree
	 */
	void exitStringOperator(WCPSParser.StringOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringScalarExpressionLabel(WCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#stringScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringScalarExpressionLabel(WCPSParser.StringScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#starExpression}.
	 * @param ctx the parse tree
	 */
	void enterStarExpressionLabel(WCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#starExpression}.
	 * @param ctx the parse tree
	 */
	void exitStarExpressionLabel(WCPSParser.StarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanSwitchCaseCoverageExpression(WCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#booleanSwitchCaseCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanSwitchCaseCoverageExpression(WCPSParser.BooleanSwitchCaseCoverageExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanSwitchCaseCombinedExpression(WCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#booleanSwitchCaseCombinedExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanSwitchCaseCombinedExpression(WCPSParser.BooleanSwitchCaseCombinedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalTrigonometricScalarExpressionLabel(WCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalTrigonometricScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalTrigonometricScalarExpressionLabel(WCPSParser.NumericalTrigonometricScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalNanNumberExpressionLabel(WCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalNanNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalNanNumberExpressionLabel(WCPSParser.NumericalNanNumberExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalRealNumberExpressionLabel(WCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalRealNumberExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalRealNumberExpressionLabel(WCPSParser.NumericalRealNumberExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalCondenseExpressionLabel(WCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalCondenseExpressionLabel(WCPSParser.NumericalCondenseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalUnaryScalarExpressionLabel(WCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalUnaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalUnaryScalarExpressionLabel(WCPSParser.NumericalUnaryScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalBinaryScalarExpressionLabel(WCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalBinaryScalarExpressionLabel}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalBinaryScalarExpressionLabel(WCPSParser.NumericalBinaryScalarExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void enterNumericalComplexNumberConstant(WCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericalComplexNumberConstant}
	 * labeled alternative in {@link WCPSParser#numericalScalarExpression}.
	 * @param ctx the parse tree
	 */
	void exitNumericalComplexNumberConstant(WCPSParser.NumericalComplexNumberConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link WCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 */
	void enterComplexNumberConstantLabel(WCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexNumberConstantLabel}
	 * labeled alternative in {@link WCPSParser#complexNumberConstant}.
	 * @param ctx the parse tree
	 */
	void exitComplexNumberConstantLabel(WCPSParser.ComplexNumberConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 */
	void enterNumericalOperator(WCPSParser.NumericalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#numericalOperator}.
	 * @param ctx the parse tree
	 */
	void exitNumericalOperator(WCPSParser.NumericalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void enterNumericalUnaryOperation(WCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#numericalUnaryOperation}.
	 * @param ctx the parse tree
	 */
	void exitNumericalUnaryOperation(WCPSParser.NumericalUnaryOperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 */
	void enterTrigonometricOperator(WCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#trigonometricOperator}.
	 * @param ctx the parse tree
	 */
	void exitTrigonometricOperator(WCPSParser.TrigonometricOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 */
	void enterGetComponentExpression(WCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#getComponentExpression}.
	 * @param ctx the parse tree
	 */
	void exitGetComponentExpression(WCPSParser.GetComponentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageIdentifierExpressionLabel(WCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageIdentifierExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageIdentifierExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageIdentifierExpressionLabel(WCPSParser.CoverageIdentifierExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageCrsSetExpressionLabel(WCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageCrsSetExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageCrsSetExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageCrsSetExpressionLabel(WCPSParser.CoverageCrsSetExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 */
	void enterDomainExpressionLabel(WCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#domainExpression}.
	 * @param ctx the parse tree
	 */
	void exitDomainExpressionLabel(WCPSParser.DomainExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsDomainByDimensionExpressionLabel(WCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsDomainByDimensionExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainByDimensionExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsDomainByDimensionExpressionLabel(WCPSParser.ImageCrsDomainByDimensionExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsDomainExpressionLabel(WCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsDomainExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsDomainExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsDomainExpressionLabel(WCPSParser.ImageCrsDomainExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 */
	void enterImageCrsExpressionLabel(WCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code imageCrsExpressionLabel}
	 * labeled alternative in {@link WCPSParser#imageCrsExpression}.
	 * @param ctx the parse tree
	 */
	void exitImageCrsExpressionLabel(WCPSParser.ImageCrsExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterDescribeCoverageExpressionLabel(WCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DescribeCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#describeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitDescribeCoverageExpressionLabel(WCPSParser.DescribeCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 */
	void enterDomainIntervals(WCPSParser.DomainIntervalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#domainIntervals}.
	 * @param ctx the parse tree
	 */
	void exitDomainIntervals(WCPSParser.DomainIntervalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#format_name}.
	 * @param ctx the parse tree
	 */
	void enterFormat_name(WCPSParser.Format_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#format_name}.
	 * @param ctx the parse tree
	 */
	void exitFormat_name(WCPSParser.Format_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#extra_params}.
	 * @param ctx the parse tree
	 */
	void enterExtra_params(WCPSParser.Extra_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#extra_params}.
	 * @param ctx the parse tree
	 */
	void exitExtra_params(WCPSParser.Extra_paramsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterEncodedCoverageExpressionLabel(WCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EncodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#encodedCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitEncodedCoverageExpressionLabel(WCPSParser.EncodedCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterDecodedCoverageExpressionLabel(WCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DecodedCoverageExpressionLabel}
	 * labeled alternative in {@link WCPSParser#decodeCoverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitDecodedCoverageExpressionLabel(WCPSParser.DecodedCoverageExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionTrigonometricLabel(WCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionTrigonometricLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionTrigonometricLabel(WCPSParser.CoverageExpressionTrigonometricLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionConstantLabel(WCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionConstantLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionConstantLabel(WCPSParser.CoverageExpressionConstantLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCoverageLabel(WCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCoverageLabel(WCPSParser.CoverageExpressionCoverageLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionShorthandSliceLabel(WCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionShorthandSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionShorthandSliceLabel(WCPSParser.CoverageExpressionShorthandSliceLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScaleLabel(WCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScaleLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScaleLabel(WCPSParser.CoverageExpressionScaleLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionArithmeticLabel(WCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionArithmeticLabel(WCPSParser.CoverageExpressionArithmeticLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionOverlayLabel(WCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionOverlayLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionOverlayLabel(WCPSParser.CoverageExpressionOverlayLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExponentialLabel(WCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExponentialLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExponentialLabel(WCPSParser.CoverageExpressionExponentialLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionLogicLabel(WCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionLogicLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionLogicLabel(WCPSParser.CoverageExpressionLogicLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionComparissonLabel(WCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionComparissonLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionComparissonLabel(WCPSParser.CoverageExpressionComparissonLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScaleByDomainIntervalsLabel(WCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScaleByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScaleByDomainIntervalsLabel(WCPSParser.CoverageExpressionScaleByDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionSliceLabel(WCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionSliceLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionSliceLabel(WCPSParser.CoverageExpressionSliceLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionRangeSubsettingLabel(WCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionRangeSubsettingLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionRangeSubsettingLabel(WCPSParser.CoverageExpressionRangeSubsettingLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionUnaryBooleanLabel(WCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionUnaryBooleanLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionUnaryBooleanLabel(WCPSParser.CoverageExpressionUnaryBooleanLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionDomainIntervalsLabel(WCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionDomainIntervalsLabel(WCPSParser.CoverageExpressionDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionShorthandTrimLabel(WCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionShorthandTrimLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionShorthandTrimLabel(WCPSParser.CoverageExpressionShorthandTrimLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionVariableNameLabel(WCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionVariableNameLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionVariableNameLabel(WCPSParser.CoverageExpressionVariableNameLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionScalarLabel(WCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionScalarLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionScalarLabel(WCPSParser.CoverageExpressionScalarLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionRangeConstructorLabel(WCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionRangeConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionRangeConstructorLabel(WCPSParser.CoverageExpressionRangeConstructorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExtendByDomainIntervalsLabel(WCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExtendByDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExtendByDomainIntervalsLabel(WCPSParser.CoverageExpressionExtendByDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionTrimCoverageLabel(WCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionTrimCoverageLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionTrimCoverageLabel(WCPSParser.CoverageExpressionTrimCoverageLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCastLabel(WCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCastLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCastLabel(WCPSParser.CoverageExpressionCastLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionPowerLabel(WCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionPowerLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionPowerLabel(WCPSParser.CoverageExpressionPowerLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionConstructorLabel(WCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionConstructorLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionConstructorLabel(WCPSParser.CoverageExpressionConstructorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionCrsTransformLabel(WCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionCrsTransformLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionCrsTransformLabel(WCPSParser.CoverageExpressionCrsTransformLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionDecodeLabel(WCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionDecodeLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionDecodeLabel(WCPSParser.CoverageExpressionDecodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionExtendLabel(WCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionExtendLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionExtendLabel(WCPSParser.CoverageExpressionExtendLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionUnaryArithmeticLabel(WCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionUnaryArithmeticLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionUnaryArithmeticLabel(WCPSParser.CoverageExpressionUnaryArithmeticLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageExpressionSwitchCaseLabel(WCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageExpressionSwitchCaseLabel}
	 * labeled alternative in {@link WCPSParser#coverageExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageExpressionSwitchCaseLabel(WCPSParser.CoverageExpressionSwitchCaseLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void enterCoverageArithmeticOperator(WCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#coverageArithmeticOperator}.
	 * @param ctx the parse tree
	 */
	void exitCoverageArithmeticOperator(WCPSParser.CoverageArithmeticOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryArithmeticExpressionOperator(WCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#unaryArithmeticExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryArithmeticExpressionOperator(WCPSParser.UnaryArithmeticExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryCoverageArithmeticExpressionLabel(WCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryCoverageArithmeticExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryArithmeticExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryCoverageArithmeticExpressionLabel(WCPSParser.UnaryCoverageArithmeticExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link WCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 */
	void enterTrigonometricExpressionLabel(WCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrigonometricExpressionLabel}
	 * labeled alternative in {@link WCPSParser#trigonometricExpression}.
	 * @param ctx the parse tree
	 */
	void exitTrigonometricExpressionLabel(WCPSParser.TrigonometricExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterExponentialExpressionOperator(WCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#exponentialExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitExponentialExpressionOperator(WCPSParser.ExponentialExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link WCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 */
	void enterExponentialExpressionLabel(WCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExponentialExpressionLabel}
	 * labeled alternative in {@link WCPSParser#exponentialExpression}.
	 * @param ctx the parse tree
	 */
	void exitExponentialExpressionLabel(WCPSParser.ExponentialExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPowerExpressionLabel(WCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPowerExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryPowerExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPowerExpressionLabel(WCPSParser.UnaryPowerExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterNotUnaryBooleanExpressionLabel(WCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitNotUnaryBooleanExpressionLabel(WCPSParser.NotUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBitUnaryBooleanExpressionLabel(WCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitUnaryBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#unaryBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBitUnaryBooleanExpressionLabel(WCPSParser.BitUnaryBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#rangeType}.
	 * @param ctx the parse tree
	 */
	void enterRangeType(WCPSParser.RangeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#rangeType}.
	 * @param ctx the parse tree
	 */
	void exitRangeType(WCPSParser.RangeTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link WCPSParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpressionLabel(WCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastExpressionLabel}
	 * labeled alternative in {@link WCPSParser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpressionLabel(WCPSParser.CastExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(WCPSParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(WCPSParser.FieldNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void enterRangeConstructorExpressionLabel(WCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void exitRangeConstructorExpressionLabel(WCPSParser.RangeConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterRangeConstructorSwitchCaseExpressionLabel(WCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RangeConstructorSwitchCaseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#rangeConstructorSwitchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitRangeConstructorSwitchCaseExpressionLabel(WCPSParser.RangeConstructorSwitchCaseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionPointListLabel(WCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionPointListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionPointListLabel(WCPSParser.DimensionPointListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 */
	void enterDimensionPointElementLabel(WCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionPointElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionPointElement}.
	 * @param ctx the parse tree
	 */
	void exitDimensionPointElementLabel(WCPSParser.DimensionPointElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionIntervalListLabel(WCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionIntervalListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionIntervalListLabel(WCPSParser.DimensionIntervalListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void enterTrimDimensionIntervalElementLabel(WCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrimDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void exitTrimDimensionIntervalElementLabel(WCPSParser.TrimDimensionIntervalElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void enterSliceDimensionIntervalElementLabel(WCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SliceDimensionIntervalElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionIntervalElement}.
	 * @param ctx the parse tree
	 */
	void exitSliceDimensionIntervalElementLabel(WCPSParser.SliceDimensionIntervalElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link WCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 */
	void enterCrsTransformExpressionLabel(WCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CrsTransformExpressionLabel}
	 * labeled alternative in {@link WCPSParser#crsTransformExpression}.
	 * @param ctx the parse tree
	 */
	void exitCrsTransformExpressionLabel(WCPSParser.CrsTransformExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 */
	void enterDimensionCrsListLabel(WCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionCrsListLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsList}.
	 * @param ctx the parse tree
	 */
	void exitDimensionCrsListLabel(WCPSParser.DimensionCrsListLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 */
	void enterDimensionCrsElementLabel(WCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionCrsElementLabel}
	 * labeled alternative in {@link WCPSParser#dimensionCrsElement}.
	 * @param ctx the parse tree
	 */
	void exitDimensionCrsElementLabel(WCPSParser.DimensionCrsElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 */
	void enterFieldInterpolationList(WCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#fieldInterpolationList}.
	 * @param ctx the parse tree
	 */
	void exitFieldInterpolationList(WCPSParser.FieldInterpolationListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link WCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 */
	void enterFieldInterpolationListElementLabel(WCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldInterpolationListElementLabel}
	 * labeled alternative in {@link WCPSParser#fieldInterpolationListElement}.
	 * @param ctx the parse tree
	 */
	void exitFieldInterpolationListElementLabel(WCPSParser.FieldInterpolationListElementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link WCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 */
	void enterInterpolationMethodLabel(WCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InterpolationMethodLabel}
	 * labeled alternative in {@link WCPSParser#interpolationMethod}.
	 * @param ctx the parse tree
	 */
	void exitInterpolationMethodLabel(WCPSParser.InterpolationMethodLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link WCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 */
	void enterInterpolationTypeLabel(WCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InterpolationTypeLabel}
	 * labeled alternative in {@link WCPSParser#interpolationType}.
	 * @param ctx the parse tree
	 */
	void exitInterpolationTypeLabel(WCPSParser.InterpolationTypeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 */
	void enterNullResistance(WCPSParser.NullResistanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#nullResistance}.
	 * @param ctx the parse tree
	 */
	void exitNullResistance(WCPSParser.NullResistanceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageConstructorExpressionLabel(WCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstructorExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageConstructorExpressionLabel(WCPSParser.CoverageConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void enterAxisIteratorDomainIntervalsLabel(WCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisIteratorDomainIntervalsLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void exitAxisIteratorDomainIntervalsLabel(WCPSParser.AxisIteratorDomainIntervalsLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void enterAxisIteratorLabel(WCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisIteratorLabel}
	 * labeled alternative in {@link WCPSParser#axisIterator}.
	 * @param ctx the parse tree
	 */
	void exitAxisIteratorLabel(WCPSParser.AxisIteratorLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 */
	void enterIntervalExpressionLabel(WCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntervalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#intervalExpression}.
	 * @param ctx the parse tree
	 */
	void exitIntervalExpressionLabel(WCPSParser.IntervalExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 */
	void enterCoverageConstantExpressionLabel(WCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CoverageConstantExpressionLabel}
	 * labeled alternative in {@link WCPSParser#coverageConstantExpression}.
	 * @param ctx the parse tree
	 */
	void exitCoverageConstantExpressionLabel(WCPSParser.CoverageConstantExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link WCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 */
	void enterAxisSpecLabel(WCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AxisSpecLabel}
	 * labeled alternative in {@link WCPSParser#axisSpec}.
	 * @param ctx the parse tree
	 */
	void exitAxisSpecLabel(WCPSParser.AxisSpecLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCondenseExpression(WCPSParser.CondenseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#condenseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCondenseExpression(WCPSParser.CondenseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterReduceBooleanExpressionOperator(WCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#reduceBooleanExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitReduceBooleanExpressionOperator(WCPSParser.ReduceBooleanExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterReduceNumericalExpressionOperator(WCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#reduceNumericalExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitReduceNumericalExpressionOperator(WCPSParser.ReduceNumericalExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceBooleanExpressionLabel(WCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceBooleanExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceBooleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceBooleanExpressionLabel(WCPSParser.ReduceBooleanExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceNumericalExpressionLabel(WCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceNumericalExpressionLabel}
	 * labeled alternative in {@link WCPSParser#reduceNumericalExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceNumericalExpressionLabel(WCPSParser.ReduceNumericalExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpression(WCPSParser.ReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#reduceExpression}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpression(WCPSParser.ReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void enterCondenseExpressionOperator(WCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#condenseExpressionOperator}.
	 * @param ctx the parse tree
	 */
	void exitCondenseExpressionOperator(WCPSParser.CondenseExpressionOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 */
	void enterGeneralCondenseExpressionLabel(WCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GeneralCondenseExpressionLabel}
	 * labeled alternative in {@link WCPSParser#generalCondenseExpression}.
	 * @param ctx the parse tree
	 */
	void exitGeneralCondenseExpressionLabel(WCPSParser.GeneralCondenseExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCaseRangeConstructorExpressionLabel(WCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchCaseRangeConstructorExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCaseRangeConstructorExpressionLabel(WCPSParser.SwitchCaseRangeConstructorExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCaseScalarValueExpressionLabel(WCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchCaseScalarValueExpressionLabel}
	 * labeled alternative in {@link WCPSParser#switchCaseExpression}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCaseScalarValueExpressionLabel(WCPSParser.SwitchCaseScalarValueExpressionLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#crsName}.
	 * @param ctx the parse tree
	 */
	void enterCrsName(WCPSParser.CrsNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#crsName}.
	 * @param ctx the parse tree
	 */
	void exitCrsName(WCPSParser.CrsNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#axisName}.
	 * @param ctx the parse tree
	 */
	void enterAxisName(WCPSParser.AxisNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#axisName}.
	 * @param ctx the parse tree
	 */
	void exitAxisName(WCPSParser.AxisNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(WCPSParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(WCPSParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link WCPSParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(WCPSParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link WCPSParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(WCPSParser.ConstantContext ctx);
}