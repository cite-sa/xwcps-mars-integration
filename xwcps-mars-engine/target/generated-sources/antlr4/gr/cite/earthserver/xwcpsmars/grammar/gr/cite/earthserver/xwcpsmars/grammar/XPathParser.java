// Generated from gr/cite/earthserver/xwcpsmars/grammar/XPath.g4 by ANTLR 4.7
package gr.cite.earthserver.xwcpsmars.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XPathParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, FOR=4, ABSOLUTE_VALUE=5, ADD=6, ALL=7, AND=8, 
		ARCSIN=9, ARCCOS=10, ARCTAN=11, AVG=12, BIT=13, CASE=14, COLON=15, COMMA=16, 
		CONDENSE=17, COS=18, COSH=19, COUNT=20, COVERAGE=21, COVERAGE_VARIABLE_NAME_PREFIX=22, 
		CRS_TRANSFORM=23, DECODE=24, DEFAULT=25, DESCRIBE_COVERAGE=26, DIVISION=27, 
		DOT=28, DOTDOT=29, ENCODE=30, EQUAL=31, EXP=32, EXTEND=33, FALSE=34, GREATER_THAN=35, 
		GREATER_OR_EQUAL_THAN=36, IMAGINARY_PART=37, IDENTIFIER=38, CRSSET=39, 
		IMAGECRSDOMAIN=40, IMAGECRS=41, DOMAIN=42, IN=43, LEFT_BRACE=44, LEFT_BRACKET=45, 
		LEFT_PARENTHESIS=46, LN=47, LIST=48, LOG=49, LOWER_THAN=50, LOWER_OR_EQUAL_THAN=51, 
		MAX=52, MIN=53, MINUS=54, MULTIPLICATION=55, NOT=56, NOT_EQUAL=57, NAN_NUMBER_CONSTANT=58, 
		OR=59, OVER=60, OVERLAY=61, QUOTE=62, ESCAPED_QUOTE=63, PLUS=64, POWER=65, 
		REAL_PART=66, ROUND=67, RETURN=68, RIGHT_BRACE=69, RIGHT_BRACKET=70, RIGHT_PARENTHESIS=71, 
		SCALE=72, SEMICOLON=73, SIN=74, SINH=75, SLICE=76, SOME=77, SQUARE_ROOT=78, 
		STRUCT=79, SWITCH=80, TAN=81, TANH=82, TRIM=83, TRUE=84, USING=85, VALUE=86, 
		VALUES=87, WHERE=88, XOR=89, REAL_NUMBER_CONSTANT=90, SCIENTIFIC_NUMBER_CONSTANT=91, 
		COVERAGE_VARIABLE_NAME=92, NAME=93, FORMAT_NAME=94, STRING_LITERAL=95, 
		EXTRA_PARAMS=96, WS=97, AxisNameXpath=98, NodeType=99, NCName=100, IMGCRSDOMAIN=101, 
		SIMPLE_IDENTIFIER=102, SIMPLE_IDENTIFIER_WITH_NUMBERS=103, DIV=104, METADATA=105, 
		MOD=106, ASC=107, AT=108, DESC=109, DOUBLE_COLON=110, GREATER_THAN_SLASH=111, 
		ID=112, LET=113, LOWER_THAN_SLASH=114, MIXED=115, ORDERBY=116, WRAP_RESULT=117, 
		XPATH_LITERAL=118;
	public static final int
		RULE_main = 0, RULE_locationPath = 1, RULE_absoluteLocationPathNoroot = 2, 
		RULE_relativeLocationPath = 3, RULE_step = 4, RULE_axisSpecifier = 5, 
		RULE_nodeTest = 6, RULE_predicate = 7, RULE_abbreviatedStep = 8, RULE_expr = 9, 
		RULE_primaryExpr = 10, RULE_functionCall = 11, RULE_unionExprNoRoot = 12, 
		RULE_pathExprNoRoot = 13, RULE_filterExpr = 14, RULE_orExpr = 15, RULE_andExpr = 16, 
		RULE_equalityExpr = 17, RULE_relationalExpr = 18, RULE_additiveExpr = 19, 
		RULE_multiplicativeExpr = 20, RULE_unaryExprNoRoot = 21, RULE_qName = 22, 
		RULE_functionName = 23, RULE_variableReference = 24, RULE_nameTest = 25, 
		RULE_nCName = 26, RULE_wcpsHotWords = 27;
	public static final String[] ruleNames = {
		"main", "locationPath", "absoluteLocationPathNoroot", "relativeLocationPath", 
		"step", "axisSpecifier", "nodeTest", "predicate", "abbreviatedStep", "expr", 
		"primaryExpr", "functionCall", "unionExprNoRoot", "pathExprNoRoot", "filterExpr", 
		"orExpr", "andExpr", "equalityExpr", "relationalExpr", "additiveExpr", 
		"multiplicativeExpr", "unaryExprNoRoot", "qName", "functionName", "variableReference", 
		"nameTest", "nCName", "wcpsHotWords"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'//'", "'processing-instruction'", "'|'", null, null, null, null, 
		null, null, null, null, null, null, null, "':'", "','", null, null, null, 
		null, null, "'$'", null, null, null, null, "'/'", "'.'", null, null, "'='", 
		null, null, null, "'>'", "'>='", null, null, null, null, null, null, null, 
		"'{'", "'['", "'('", null, null, null, "'<'", "'<='", null, null, "'-'", 
		"'*'", null, "'!='", null, null, null, null, "'\"'", "'\\\"'", "'+'", 
		null, null, null, null, "'}'", "']'", "')'", null, "';'", null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "'@'", null, "'::'", "'/>'", 
		null, null, "'</'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "FOR", "ABSOLUTE_VALUE", "ADD", "ALL", "AND", 
		"ARCSIN", "ARCCOS", "ARCTAN", "AVG", "BIT", "CASE", "COLON", "COMMA", 
		"CONDENSE", "COS", "COSH", "COUNT", "COVERAGE", "COVERAGE_VARIABLE_NAME_PREFIX", 
		"CRS_TRANSFORM", "DECODE", "DEFAULT", "DESCRIBE_COVERAGE", "DIVISION", 
		"DOT", "DOTDOT", "ENCODE", "EQUAL", "EXP", "EXTEND", "FALSE", "GREATER_THAN", 
		"GREATER_OR_EQUAL_THAN", "IMAGINARY_PART", "IDENTIFIER", "CRSSET", "IMAGECRSDOMAIN", 
		"IMAGECRS", "DOMAIN", "IN", "LEFT_BRACE", "LEFT_BRACKET", "LEFT_PARENTHESIS", 
		"LN", "LIST", "LOG", "LOWER_THAN", "LOWER_OR_EQUAL_THAN", "MAX", "MIN", 
		"MINUS", "MULTIPLICATION", "NOT", "NOT_EQUAL", "NAN_NUMBER_CONSTANT", 
		"OR", "OVER", "OVERLAY", "QUOTE", "ESCAPED_QUOTE", "PLUS", "POWER", "REAL_PART", 
		"ROUND", "RETURN", "RIGHT_BRACE", "RIGHT_BRACKET", "RIGHT_PARENTHESIS", 
		"SCALE", "SEMICOLON", "SIN", "SINH", "SLICE", "SOME", "SQUARE_ROOT", "STRUCT", 
		"SWITCH", "TAN", "TANH", "TRIM", "TRUE", "USING", "VALUE", "VALUES", "WHERE", 
		"XOR", "REAL_NUMBER_CONSTANT", "SCIENTIFIC_NUMBER_CONSTANT", "COVERAGE_VARIABLE_NAME", 
		"NAME", "FORMAT_NAME", "STRING_LITERAL", "EXTRA_PARAMS", "WS", "AxisNameXpath", 
		"NodeType", "NCName", "IMGCRSDOMAIN", "SIMPLE_IDENTIFIER", "SIMPLE_IDENTIFIER_WITH_NUMBERS", 
		"DIV", "METADATA", "MOD", "ASC", "AT", "DESC", "DOUBLE_COLON", "GREATER_THAN_SLASH", 
		"ID", "LET", "LOWER_THAN_SLASH", "MIXED", "ORDERBY", "WRAP_RESULT", "XPATH_LITERAL"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XPathParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class MainContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitMain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MainContext main() throws RecognitionException {
		MainContext _localctx = new MainContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_main);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			expr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocationPathContext extends ParserRuleContext {
		public RelativeLocationPathContext relativeLocationPath() {
			return getRuleContext(RelativeLocationPathContext.class,0);
		}
		public AbsoluteLocationPathNorootContext absoluteLocationPathNoroot() {
			return getRuleContext(AbsoluteLocationPathNorootContext.class,0);
		}
		public LocationPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterLocationPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitLocationPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitLocationPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationPathContext locationPath() throws RecognitionException {
		LocationPathContext _localctx = new LocationPathContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_locationPath);
		try {
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case FOR:
			case ABSOLUTE_VALUE:
			case ADD:
			case ALL:
			case AND:
			case ARCSIN:
			case ARCCOS:
			case ARCTAN:
			case AVG:
			case BIT:
			case CONDENSE:
			case COS:
			case COSH:
			case COUNT:
			case COVERAGE:
			case CRS_TRANSFORM:
			case DECODE:
			case DESCRIBE_COVERAGE:
			case DOT:
			case DOTDOT:
			case ENCODE:
			case EXP:
			case EXTEND:
			case FALSE:
			case IMAGINARY_PART:
			case IN:
			case LN:
			case LIST:
			case LOG:
			case MAX:
			case MIN:
			case MULTIPLICATION:
			case NOT:
			case OR:
			case OVER:
			case OVERLAY:
			case POWER:
			case REAL_PART:
			case ROUND:
			case RETURN:
			case SCALE:
			case SIN:
			case SINH:
			case SLICE:
			case SOME:
			case SQUARE_ROOT:
			case STRUCT:
			case TAN:
			case TANH:
			case TRIM:
			case TRUE:
			case USING:
			case VALUE:
			case VALUES:
			case WHERE:
			case XOR:
			case AxisNameXpath:
			case NodeType:
			case NCName:
			case IMGCRSDOMAIN:
			case SIMPLE_IDENTIFIER:
			case SIMPLE_IDENTIFIER_WITH_NUMBERS:
			case DIV:
			case METADATA:
			case MOD:
			case AT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				relativeLocationPath();
				}
				break;
			case T__0:
			case DIVISION:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				absoluteLocationPathNoroot();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbsoluteLocationPathNorootContext extends ParserRuleContext {
		public RelativeLocationPathContext relativeLocationPath() {
			return getRuleContext(RelativeLocationPathContext.class,0);
		}
		public AbsoluteLocationPathNorootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_absoluteLocationPathNoroot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAbsoluteLocationPathNoroot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAbsoluteLocationPathNoroot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAbsoluteLocationPathNoroot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbsoluteLocationPathNorootContext absoluteLocationPathNoroot() throws RecognitionException {
		AbsoluteLocationPathNorootContext _localctx = new AbsoluteLocationPathNorootContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_absoluteLocationPathNoroot);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DIVISION:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				match(DIVISION);
				setState(63);
				relativeLocationPath();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(T__0);
				setState(65);
				relativeLocationPath();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelativeLocationPathContext extends ParserRuleContext {
		public List<StepContext> step() {
			return getRuleContexts(StepContext.class);
		}
		public StepContext step(int i) {
			return getRuleContext(StepContext.class,i);
		}
		public RelativeLocationPathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relativeLocationPath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelativeLocationPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelativeLocationPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitRelativeLocationPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelativeLocationPathContext relativeLocationPath() throws RecognitionException {
		RelativeLocationPathContext _localctx = new RelativeLocationPathContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_relativeLocationPath);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			step();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==DIVISION) {
				{
				{
				setState(69);
				_la = _input.LA(1);
				if ( !(_la==T__0 || _la==DIVISION) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(70);
				step();
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StepContext extends ParserRuleContext {
		public AxisSpecifierContext axisSpecifier() {
			return getRuleContext(AxisSpecifierContext.class,0);
		}
		public NodeTestContext nodeTest() {
			return getRuleContext(NodeTestContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public AbbreviatedStepContext abbreviatedStep() {
			return getRuleContext(AbbreviatedStepContext.class,0);
		}
		public StepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepContext step() throws RecognitionException {
		StepContext _localctx = new StepContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_step);
		int _la;
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case FOR:
			case ABSOLUTE_VALUE:
			case ADD:
			case ALL:
			case AND:
			case ARCSIN:
			case ARCCOS:
			case ARCTAN:
			case AVG:
			case BIT:
			case CONDENSE:
			case COS:
			case COSH:
			case COUNT:
			case COVERAGE:
			case CRS_TRANSFORM:
			case DECODE:
			case DESCRIBE_COVERAGE:
			case ENCODE:
			case EXP:
			case EXTEND:
			case FALSE:
			case IMAGINARY_PART:
			case IN:
			case LN:
			case LIST:
			case LOG:
			case MAX:
			case MIN:
			case MULTIPLICATION:
			case NOT:
			case OR:
			case OVER:
			case OVERLAY:
			case POWER:
			case REAL_PART:
			case ROUND:
			case RETURN:
			case SCALE:
			case SIN:
			case SINH:
			case SLICE:
			case SOME:
			case SQUARE_ROOT:
			case STRUCT:
			case TAN:
			case TANH:
			case TRIM:
			case TRUE:
			case USING:
			case VALUE:
			case VALUES:
			case WHERE:
			case XOR:
			case AxisNameXpath:
			case NodeType:
			case NCName:
			case IMGCRSDOMAIN:
			case SIMPLE_IDENTIFIER:
			case SIMPLE_IDENTIFIER_WITH_NUMBERS:
			case DIV:
			case METADATA:
			case MOD:
			case AT:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				axisSpecifier();
				setState(77);
				nodeTest();
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LEFT_BRACKET) {
					{
					{
					setState(78);
					predicate();
					}
					}
					setState(83);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DOT:
			case DOTDOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				abbreviatedStep();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AxisSpecifierContext extends ParserRuleContext {
		public TerminalNode AxisNameXpath() { return getToken(XPathParser.AxisNameXpath, 0); }
		public AxisSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_axisSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAxisSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAxisSpecifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAxisSpecifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AxisSpecifierContext axisSpecifier() throws RecognitionException {
		AxisSpecifierContext _localctx = new AxisSpecifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_axisSpecifier);
		int _la;
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				match(AxisNameXpath);
				setState(88);
				match(DOUBLE_COLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AT) {
					{
					setState(89);
					match(AT);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeTestContext extends ParserRuleContext {
		public NameTestContext nameTest() {
			return getRuleContext(NameTestContext.class,0);
		}
		public TerminalNode NodeType() { return getToken(XPathParser.NodeType, 0); }
		public TerminalNode LEFT_PARENTHESIS() { return getToken(XPathParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(XPathParser.RIGHT_PARENTHESIS, 0); }
		public TerminalNode XPATH_LITERAL() { return getToken(XPathParser.XPATH_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(XPathParser.STRING_LITERAL, 0); }
		public NodeTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNodeTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNodeTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitNodeTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeTestContext nodeTest() throws RecognitionException {
		NodeTestContext _localctx = new NodeTestContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_nodeTest);
		int _la;
		try {
			setState(102);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
			case ABSOLUTE_VALUE:
			case ADD:
			case ALL:
			case AND:
			case ARCSIN:
			case ARCCOS:
			case ARCTAN:
			case AVG:
			case BIT:
			case CONDENSE:
			case COS:
			case COSH:
			case COUNT:
			case COVERAGE:
			case CRS_TRANSFORM:
			case DECODE:
			case DESCRIBE_COVERAGE:
			case ENCODE:
			case EXP:
			case EXTEND:
			case FALSE:
			case IMAGINARY_PART:
			case IN:
			case LN:
			case LIST:
			case LOG:
			case MAX:
			case MIN:
			case MULTIPLICATION:
			case NOT:
			case OR:
			case OVER:
			case OVERLAY:
			case POWER:
			case REAL_PART:
			case ROUND:
			case RETURN:
			case SCALE:
			case SIN:
			case SINH:
			case SLICE:
			case SOME:
			case SQUARE_ROOT:
			case STRUCT:
			case TAN:
			case TANH:
			case TRIM:
			case TRUE:
			case USING:
			case VALUE:
			case VALUES:
			case WHERE:
			case XOR:
			case AxisNameXpath:
			case NCName:
			case IMGCRSDOMAIN:
			case SIMPLE_IDENTIFIER:
			case SIMPLE_IDENTIFIER_WITH_NUMBERS:
			case DIV:
			case METADATA:
			case MOD:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				nameTest();
				}
				break;
			case NodeType:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				match(NodeType);
				setState(96);
				match(LEFT_PARENTHESIS);
				setState(97);
				match(RIGHT_PARENTHESIS);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(98);
				match(T__1);
				setState(99);
				match(LEFT_PARENTHESIS);
				setState(100);
				_la = _input.LA(1);
				if ( !(_la==STRING_LITERAL || _la==XPATH_LITERAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(101);
				match(RIGHT_PARENTHESIS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PredicateContext extends ParserRuleContext {
		public TerminalNode LEFT_BRACKET() { return getToken(XPathParser.LEFT_BRACKET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(XPathParser.RIGHT_BRACKET, 0); }
		public PredicateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predicate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPredicate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPredicate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitPredicate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PredicateContext predicate() throws RecognitionException {
		PredicateContext _localctx = new PredicateContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_predicate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(LEFT_BRACKET);
			setState(105);
			expr();
			setState(106);
			match(RIGHT_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbbreviatedStepContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(XPathParser.DOT, 0); }
		public TerminalNode DOTDOT() { return getToken(XPathParser.DOTDOT, 0); }
		public AbbreviatedStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abbreviatedStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAbbreviatedStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAbbreviatedStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAbbreviatedStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbbreviatedStepContext abbreviatedStep() throws RecognitionException {
		AbbreviatedStepContext _localctx = new AbbreviatedStepContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_abbreviatedStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_la = _input.LA(1);
			if ( !(_la==DOT || _la==DOTDOT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public OrExprContext orExpr() {
			return getRuleContext(OrExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			orExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimaryExprContext extends ParserRuleContext {
		public VariableReferenceContext variableReference() {
			return getRuleContext(VariableReferenceContext.class,0);
		}
		public TerminalNode LEFT_PARENTHESIS() { return getToken(XPathParser.LEFT_PARENTHESIS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(XPathParser.RIGHT_PARENTHESIS, 0); }
		public TerminalNode XPATH_LITERAL() { return getToken(XPathParser.XPATH_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(XPathParser.STRING_LITERAL, 0); }
		public TerminalNode REAL_NUMBER_CONSTANT() { return getToken(XPathParser.REAL_NUMBER_CONSTANT, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public PrimaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPrimaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPrimaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitPrimaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExprContext primaryExpr() throws RecognitionException {
		PrimaryExprContext _localctx = new PrimaryExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_primaryExpr);
		int _la;
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COVERAGE_VARIABLE_NAME_PREFIX:
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				variableReference();
				}
				break;
			case LEFT_PARENTHESIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(LEFT_PARENTHESIS);
				setState(114);
				expr();
				setState(115);
				match(RIGHT_PARENTHESIS);
				}
				break;
			case STRING_LITERAL:
			case XPATH_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				_la = _input.LA(1);
				if ( !(_la==STRING_LITERAL || _la==XPATH_LITERAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case REAL_NUMBER_CONSTANT:
				enterOuterAlt(_localctx, 4);
				{
				setState(118);
				match(REAL_NUMBER_CONSTANT);
				}
				break;
			case FOR:
			case ABSOLUTE_VALUE:
			case ADD:
			case ALL:
			case AND:
			case ARCSIN:
			case ARCCOS:
			case ARCTAN:
			case AVG:
			case BIT:
			case CONDENSE:
			case COS:
			case COSH:
			case COUNT:
			case COVERAGE:
			case CRS_TRANSFORM:
			case DECODE:
			case DESCRIBE_COVERAGE:
			case ENCODE:
			case EXP:
			case EXTEND:
			case FALSE:
			case IMAGINARY_PART:
			case IN:
			case LN:
			case LIST:
			case LOG:
			case MAX:
			case MIN:
			case NOT:
			case OR:
			case OVER:
			case OVERLAY:
			case POWER:
			case REAL_PART:
			case ROUND:
			case RETURN:
			case SCALE:
			case SIN:
			case SINH:
			case SLICE:
			case SOME:
			case SQUARE_ROOT:
			case STRUCT:
			case TAN:
			case TANH:
			case TRIM:
			case TRUE:
			case USING:
			case VALUE:
			case VALUES:
			case WHERE:
			case XOR:
			case AxisNameXpath:
			case NCName:
			case IMGCRSDOMAIN:
			case SIMPLE_IDENTIFIER:
			case SIMPLE_IDENTIFIER_WITH_NUMBERS:
			case DIV:
			case METADATA:
			case MOD:
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(119);
				functionCall();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode LEFT_PARENTHESIS() { return getToken(XPathParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(XPathParser.RIGHT_PARENTHESIS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			functionName();
			setState(123);
			match(LEFT_PARENTHESIS);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << FOR) | (1L << ABSOLUTE_VALUE) | (1L << ADD) | (1L << ALL) | (1L << AND) | (1L << ARCSIN) | (1L << ARCCOS) | (1L << ARCTAN) | (1L << AVG) | (1L << BIT) | (1L << CONDENSE) | (1L << COS) | (1L << COSH) | (1L << COUNT) | (1L << COVERAGE) | (1L << COVERAGE_VARIABLE_NAME_PREFIX) | (1L << CRS_TRANSFORM) | (1L << DECODE) | (1L << DESCRIBE_COVERAGE) | (1L << DIVISION) | (1L << DOT) | (1L << DOTDOT) | (1L << ENCODE) | (1L << EXP) | (1L << EXTEND) | (1L << FALSE) | (1L << IMAGINARY_PART) | (1L << IN) | (1L << LEFT_PARENTHESIS) | (1L << LN) | (1L << LIST) | (1L << LOG) | (1L << MAX) | (1L << MIN) | (1L << MINUS) | (1L << MULTIPLICATION) | (1L << NOT) | (1L << OR) | (1L << OVER) | (1L << OVERLAY))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (POWER - 65)) | (1L << (REAL_PART - 65)) | (1L << (ROUND - 65)) | (1L << (RETURN - 65)) | (1L << (SCALE - 65)) | (1L << (SIN - 65)) | (1L << (SINH - 65)) | (1L << (SLICE - 65)) | (1L << (SOME - 65)) | (1L << (SQUARE_ROOT - 65)) | (1L << (STRUCT - 65)) | (1L << (TAN - 65)) | (1L << (TANH - 65)) | (1L << (TRIM - 65)) | (1L << (TRUE - 65)) | (1L << (USING - 65)) | (1L << (VALUE - 65)) | (1L << (VALUES - 65)) | (1L << (WHERE - 65)) | (1L << (XOR - 65)) | (1L << (REAL_NUMBER_CONSTANT - 65)) | (1L << (STRING_LITERAL - 65)) | (1L << (AxisNameXpath - 65)) | (1L << (NodeType - 65)) | (1L << (NCName - 65)) | (1L << (IMGCRSDOMAIN - 65)) | (1L << (SIMPLE_IDENTIFIER - 65)) | (1L << (SIMPLE_IDENTIFIER_WITH_NUMBERS - 65)) | (1L << (DIV - 65)) | (1L << (METADATA - 65)) | (1L << (MOD - 65)) | (1L << (AT - 65)) | (1L << (ID - 65)) | (1L << (XPATH_LITERAL - 65)))) != 0)) {
				{
				setState(124);
				expr();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(125);
					match(COMMA);
					setState(126);
					expr();
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(134);
			match(RIGHT_PARENTHESIS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionExprNoRootContext extends ParserRuleContext {
		public PathExprNoRootContext pathExprNoRoot() {
			return getRuleContext(PathExprNoRootContext.class,0);
		}
		public UnionExprNoRootContext unionExprNoRoot() {
			return getRuleContext(UnionExprNoRootContext.class,0);
		}
		public UnionExprNoRootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionExprNoRoot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnionExprNoRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnionExprNoRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitUnionExprNoRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionExprNoRootContext unionExprNoRoot() throws RecognitionException {
		UnionExprNoRootContext _localctx = new UnionExprNoRootContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_unionExprNoRoot);
		int _la;
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				pathExprNoRoot();
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(137);
					match(T__2);
					setState(138);
					unionExprNoRoot();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				match(DIVISION);
				setState(142);
				match(T__2);
				setState(143);
				unionExprNoRoot();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathExprNoRootContext extends ParserRuleContext {
		public LocationPathContext locationPath() {
			return getRuleContext(LocationPathContext.class,0);
		}
		public FilterExprContext filterExpr() {
			return getRuleContext(FilterExprContext.class,0);
		}
		public RelativeLocationPathContext relativeLocationPath() {
			return getRuleContext(RelativeLocationPathContext.class,0);
		}
		public PathExprNoRootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathExprNoRoot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterPathExprNoRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitPathExprNoRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitPathExprNoRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathExprNoRootContext pathExprNoRoot() throws RecognitionException {
		PathExprNoRootContext _localctx = new PathExprNoRootContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pathExprNoRoot);
		int _la;
		try {
			setState(152);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				locationPath();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				filterExpr();
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__0 || _la==DIVISION) {
					{
					setState(148);
					_la = _input.LA(1);
					if ( !(_la==T__0 || _la==DIVISION) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(149);
					relativeLocationPath();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterExprContext extends ParserRuleContext {
		public PrimaryExprContext primaryExpr() {
			return getRuleContext(PrimaryExprContext.class,0);
		}
		public List<PredicateContext> predicate() {
			return getRuleContexts(PredicateContext.class);
		}
		public PredicateContext predicate(int i) {
			return getRuleContext(PredicateContext.class,i);
		}
		public FilterExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFilterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFilterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitFilterExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExprContext filterExpr() throws RecognitionException {
		FilterExprContext _localctx = new FilterExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_filterExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			primaryExpr();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LEFT_BRACKET) {
				{
				{
				setState(155);
				predicate();
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExprContext extends ParserRuleContext {
		public List<AndExprContext> andExpr() {
			return getRuleContexts(AndExprContext.class);
		}
		public AndExprContext andExpr(int i) {
			return getRuleContext(AndExprContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(XPathParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(XPathParser.OR, i);
		}
		public OrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExprContext orExpr() throws RecognitionException {
		OrExprContext _localctx = new OrExprContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_orExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			andExpr();
			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(162);
				match(OR);
				setState(163);
				andExpr();
				}
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExprContext extends ParserRuleContext {
		public List<EqualityExprContext> equalityExpr() {
			return getRuleContexts(EqualityExprContext.class);
		}
		public EqualityExprContext equalityExpr(int i) {
			return getRuleContext(EqualityExprContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(XPathParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(XPathParser.AND, i);
		}
		public AndExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExprContext andExpr() throws RecognitionException {
		AndExprContext _localctx = new AndExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_andExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			equalityExpr();
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(170);
				match(AND);
				setState(171);
				equalityExpr();
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExprContext extends ParserRuleContext {
		public List<RelationalExprContext> relationalExpr() {
			return getRuleContexts(RelationalExprContext.class);
		}
		public RelationalExprContext relationalExpr(int i) {
			return getRuleContext(RelationalExprContext.class,i);
		}
		public EqualityExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterEqualityExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitEqualityExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitEqualityExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExprContext equalityExpr() throws RecognitionException {
		EqualityExprContext _localctx = new EqualityExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_equalityExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			relationalExpr();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQUAL || _la==NOT_EQUAL) {
				{
				{
				setState(178);
				_la = _input.LA(1);
				if ( !(_la==EQUAL || _la==NOT_EQUAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(179);
				relationalExpr();
				}
				}
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public List<TerminalNode> LOWER_THAN() { return getTokens(XPathParser.LOWER_THAN); }
		public TerminalNode LOWER_THAN(int i) {
			return getToken(XPathParser.LOWER_THAN, i);
		}
		public List<TerminalNode> GREATER_THAN() { return getTokens(XPathParser.GREATER_THAN); }
		public TerminalNode GREATER_THAN(int i) {
			return getToken(XPathParser.GREATER_THAN, i);
		}
		public List<TerminalNode> LOWER_OR_EQUAL_THAN() { return getTokens(XPathParser.LOWER_OR_EQUAL_THAN); }
		public TerminalNode LOWER_OR_EQUAL_THAN(int i) {
			return getToken(XPathParser.LOWER_OR_EQUAL_THAN, i);
		}
		public List<TerminalNode> GREATER_OR_EQUAL_THAN() { return getTokens(XPathParser.GREATER_OR_EQUAL_THAN); }
		public TerminalNode GREATER_OR_EQUAL_THAN(int i) {
			return getToken(XPathParser.GREATER_OR_EQUAL_THAN, i);
		}
		public RelationalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitRelationalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitRelationalExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExprContext relationalExpr() throws RecognitionException {
		RelationalExprContext _localctx = new RelationalExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_relationalExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			additiveExpr();
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_THAN) | (1L << GREATER_OR_EQUAL_THAN) | (1L << LOWER_THAN) | (1L << LOWER_OR_EQUAL_THAN))) != 0)) {
				{
				{
				setState(186);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_THAN) | (1L << GREATER_OR_EQUAL_THAN) | (1L << LOWER_THAN) | (1L << LOWER_OR_EQUAL_THAN))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(187);
				additiveExpr();
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			multiplicativeExpr();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MINUS || _la==PLUS) {
				{
				{
				setState(194);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==PLUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(195);
				multiplicativeExpr();
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExprContext extends ParserRuleContext {
		public UnaryExprNoRootContext unaryExprNoRoot() {
			return getRuleContext(UnaryExprNoRootContext.class,0);
		}
		public MultiplicativeExprContext multiplicativeExpr() {
			return getRuleContext(MultiplicativeExprContext.class,0);
		}
		public TerminalNode DIV() { return getToken(XPathParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(XPathParser.MOD, 0); }
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_multiplicativeExpr);
		int _la;
		try {
			setState(211);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				unaryExprNoRoot();
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (MULTIPLICATION - 55)) | (1L << (DIV - 55)) | (1L << (MOD - 55)))) != 0)) {
					{
					setState(202);
					_la = _input.LA(1);
					if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (MULTIPLICATION - 55)) | (1L << (DIV - 55)) | (1L << (MOD - 55)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(203);
					multiplicativeExpr();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(DIVISION);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DIV || _la==MOD) {
					{
					setState(207);
					_la = _input.LA(1);
					if ( !(_la==DIV || _la==MOD) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(208);
					multiplicativeExpr();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnaryExprNoRootContext extends ParserRuleContext {
		public UnionExprNoRootContext unionExprNoRoot() {
			return getRuleContext(UnionExprNoRootContext.class,0);
		}
		public UnaryExprNoRootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExprNoRoot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterUnaryExprNoRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitUnaryExprNoRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitUnaryExprNoRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprNoRootContext unaryExprNoRoot() throws RecognitionException {
		UnaryExprNoRootContext _localctx = new UnaryExprNoRootContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_unaryExprNoRoot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MINUS) {
				{
				{
				setState(213);
				match(MINUS);
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(219);
			unionExprNoRoot();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QNameContext extends ParserRuleContext {
		public List<NCNameContext> nCName() {
			return getRuleContexts(NCNameContext.class);
		}
		public NCNameContext nCName(int i) {
			return getRuleContext(NCNameContext.class,i);
		}
		public QNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterQName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitQName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitQName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QNameContext qName() throws RecognitionException {
		QNameContext _localctx = new QNameContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_qName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			nCName();
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(222);
				match(COLON);
				setState(223);
				nCName();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionNameContext extends ParserRuleContext {
		public QNameContext qName() {
			return getRuleContext(QNameContext.class,0);
		}
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_functionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			qName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableReferenceContext extends ParserRuleContext {
		public QNameContext qName() {
			return getRuleContext(QNameContext.class,0);
		}
		public VariableReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableReference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterVariableReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitVariableReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitVariableReference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableReferenceContext variableReference() throws RecognitionException {
		VariableReferenceContext _localctx = new VariableReferenceContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_variableReference);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			match(COVERAGE_VARIABLE_NAME_PREFIX);
			setState(229);
			qName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameTestContext extends ParserRuleContext {
		public NCNameContext nCName() {
			return getRuleContext(NCNameContext.class,0);
		}
		public QNameContext qName() {
			return getRuleContext(QNameContext.class,0);
		}
		public NameTestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameTest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNameTest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNameTest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitNameTest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameTestContext nameTest() throws RecognitionException {
		NameTestContext _localctx = new NameTestContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_nameTest);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(231);
				match(MULTIPLICATION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232);
				nCName();
				setState(233);
				match(COLON);
				setState(234);
				match(MULTIPLICATION);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236);
				qName();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NCNameContext extends ParserRuleContext {
		public TerminalNode NCName() { return getToken(XPathParser.NCName, 0); }
		public TerminalNode SIMPLE_IDENTIFIER_WITH_NUMBERS() { return getToken(XPathParser.SIMPLE_IDENTIFIER_WITH_NUMBERS, 0); }
		public TerminalNode SIMPLE_IDENTIFIER() { return getToken(XPathParser.SIMPLE_IDENTIFIER, 0); }
		public TerminalNode AxisNameXpath() { return getToken(XPathParser.AxisNameXpath, 0); }
		public WcpsHotWordsContext wcpsHotWords() {
			return getRuleContext(WcpsHotWordsContext.class,0);
		}
		public NCNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nCName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterNCName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitNCName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitNCName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NCNameContext nCName() throws RecognitionException {
		NCNameContext _localctx = new NCNameContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_nCName);
		try {
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NCName:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(NCName);
				}
				break;
			case SIMPLE_IDENTIFIER_WITH_NUMBERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				match(SIMPLE_IDENTIFIER_WITH_NUMBERS);
				}
				break;
			case SIMPLE_IDENTIFIER:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(SIMPLE_IDENTIFIER);
				}
				break;
			case AxisNameXpath:
				enterOuterAlt(_localctx, 4);
				{
				setState(242);
				match(AxisNameXpath);
				}
				break;
			case FOR:
			case ABSOLUTE_VALUE:
			case ADD:
			case ALL:
			case AND:
			case ARCSIN:
			case ARCCOS:
			case ARCTAN:
			case AVG:
			case BIT:
			case CONDENSE:
			case COS:
			case COSH:
			case COUNT:
			case COVERAGE:
			case CRS_TRANSFORM:
			case DECODE:
			case DESCRIBE_COVERAGE:
			case ENCODE:
			case EXP:
			case EXTEND:
			case FALSE:
			case IMAGINARY_PART:
			case IN:
			case LN:
			case LIST:
			case LOG:
			case MAX:
			case MIN:
			case NOT:
			case OR:
			case OVER:
			case OVERLAY:
			case POWER:
			case REAL_PART:
			case ROUND:
			case RETURN:
			case SCALE:
			case SIN:
			case SINH:
			case SLICE:
			case SOME:
			case SQUARE_ROOT:
			case STRUCT:
			case TAN:
			case TANH:
			case TRIM:
			case TRUE:
			case USING:
			case VALUE:
			case VALUES:
			case WHERE:
			case XOR:
			case IMGCRSDOMAIN:
			case DIV:
			case METADATA:
			case MOD:
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(243);
				wcpsHotWords();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WcpsHotWordsContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(XPathParser.FOR, 0); }
		public TerminalNode ABSOLUTE_VALUE() { return getToken(XPathParser.ABSOLUTE_VALUE, 0); }
		public TerminalNode ADD() { return getToken(XPathParser.ADD, 0); }
		public TerminalNode ALL() { return getToken(XPathParser.ALL, 0); }
		public TerminalNode AND() { return getToken(XPathParser.AND, 0); }
		public TerminalNode ARCSIN() { return getToken(XPathParser.ARCSIN, 0); }
		public TerminalNode ARCCOS() { return getToken(XPathParser.ARCCOS, 0); }
		public TerminalNode ARCTAN() { return getToken(XPathParser.ARCTAN, 0); }
		public TerminalNode AVG() { return getToken(XPathParser.AVG, 0); }
		public TerminalNode BIT() { return getToken(XPathParser.BIT, 0); }
		public TerminalNode CONDENSE() { return getToken(XPathParser.CONDENSE, 0); }
		public TerminalNode COS() { return getToken(XPathParser.COS, 0); }
		public TerminalNode COSH() { return getToken(XPathParser.COSH, 0); }
		public TerminalNode COUNT() { return getToken(XPathParser.COUNT, 0); }
		public TerminalNode COVERAGE() { return getToken(XPathParser.COVERAGE, 0); }
		public TerminalNode CRS_TRANSFORM() { return getToken(XPathParser.CRS_TRANSFORM, 0); }
		public TerminalNode DECODE() { return getToken(XPathParser.DECODE, 0); }
		public TerminalNode DESCRIBE_COVERAGE() { return getToken(XPathParser.DESCRIBE_COVERAGE, 0); }
		public TerminalNode DIV() { return getToken(XPathParser.DIV, 0); }
		public TerminalNode ENCODE() { return getToken(XPathParser.ENCODE, 0); }
		public TerminalNode EXP() { return getToken(XPathParser.EXP, 0); }
		public TerminalNode EXTEND() { return getToken(XPathParser.EXTEND, 0); }
		public TerminalNode FALSE() { return getToken(XPathParser.FALSE, 0); }
		public TerminalNode IMAGINARY_PART() { return getToken(XPathParser.IMAGINARY_PART, 0); }
		public TerminalNode ID() { return getToken(XPathParser.ID, 0); }
		public TerminalNode IMGCRSDOMAIN() { return getToken(XPathParser.IMGCRSDOMAIN, 0); }
		public TerminalNode IN() { return getToken(XPathParser.IN, 0); }
		public TerminalNode LN() { return getToken(XPathParser.LN, 0); }
		public TerminalNode LIST() { return getToken(XPathParser.LIST, 0); }
		public TerminalNode LOG() { return getToken(XPathParser.LOG, 0); }
		public TerminalNode MAX() { return getToken(XPathParser.MAX, 0); }
		public TerminalNode MIN() { return getToken(XPathParser.MIN, 0); }
		public TerminalNode METADATA() { return getToken(XPathParser.METADATA, 0); }
		public TerminalNode MOD() { return getToken(XPathParser.MOD, 0); }
		public TerminalNode NOT() { return getToken(XPathParser.NOT, 0); }
		public TerminalNode OR() { return getToken(XPathParser.OR, 0); }
		public TerminalNode OVER() { return getToken(XPathParser.OVER, 0); }
		public TerminalNode OVERLAY() { return getToken(XPathParser.OVERLAY, 0); }
		public TerminalNode POWER() { return getToken(XPathParser.POWER, 0); }
		public TerminalNode REAL_PART() { return getToken(XPathParser.REAL_PART, 0); }
		public TerminalNode ROUND() { return getToken(XPathParser.ROUND, 0); }
		public TerminalNode RETURN() { return getToken(XPathParser.RETURN, 0); }
		public TerminalNode SCALE() { return getToken(XPathParser.SCALE, 0); }
		public TerminalNode SIN() { return getToken(XPathParser.SIN, 0); }
		public TerminalNode SINH() { return getToken(XPathParser.SINH, 0); }
		public TerminalNode SLICE() { return getToken(XPathParser.SLICE, 0); }
		public TerminalNode SOME() { return getToken(XPathParser.SOME, 0); }
		public TerminalNode SQUARE_ROOT() { return getToken(XPathParser.SQUARE_ROOT, 0); }
		public TerminalNode STRUCT() { return getToken(XPathParser.STRUCT, 0); }
		public TerminalNode TAN() { return getToken(XPathParser.TAN, 0); }
		public TerminalNode TANH() { return getToken(XPathParser.TANH, 0); }
		public TerminalNode TRIM() { return getToken(XPathParser.TRIM, 0); }
		public TerminalNode TRUE() { return getToken(XPathParser.TRUE, 0); }
		public TerminalNode USING() { return getToken(XPathParser.USING, 0); }
		public TerminalNode VALUE() { return getToken(XPathParser.VALUE, 0); }
		public TerminalNode VALUES() { return getToken(XPathParser.VALUES, 0); }
		public TerminalNode WHERE() { return getToken(XPathParser.WHERE, 0); }
		public TerminalNode XOR() { return getToken(XPathParser.XOR, 0); }
		public WcpsHotWordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wcpsHotWords; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).enterWcpsHotWords(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XPathListener ) ((XPathListener)listener).exitWcpsHotWords(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XPathVisitor ) return ((XPathVisitor<? extends T>)visitor).visitWcpsHotWords(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WcpsHotWordsContext wcpsHotWords() throws RecognitionException {
		WcpsHotWordsContext _localctx = new WcpsHotWordsContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_wcpsHotWords);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FOR) | (1L << ABSOLUTE_VALUE) | (1L << ADD) | (1L << ALL) | (1L << AND) | (1L << ARCSIN) | (1L << ARCCOS) | (1L << ARCTAN) | (1L << AVG) | (1L << BIT) | (1L << CONDENSE) | (1L << COS) | (1L << COSH) | (1L << COUNT) | (1L << COVERAGE) | (1L << CRS_TRANSFORM) | (1L << DECODE) | (1L << DESCRIBE_COVERAGE) | (1L << ENCODE) | (1L << EXP) | (1L << EXTEND) | (1L << FALSE) | (1L << IMAGINARY_PART) | (1L << IN) | (1L << LN) | (1L << LIST) | (1L << LOG) | (1L << MAX) | (1L << MIN) | (1L << NOT) | (1L << OR) | (1L << OVER) | (1L << OVERLAY))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (POWER - 65)) | (1L << (REAL_PART - 65)) | (1L << (ROUND - 65)) | (1L << (RETURN - 65)) | (1L << (SCALE - 65)) | (1L << (SIN - 65)) | (1L << (SINH - 65)) | (1L << (SLICE - 65)) | (1L << (SOME - 65)) | (1L << (SQUARE_ROOT - 65)) | (1L << (STRUCT - 65)) | (1L << (TAN - 65)) | (1L << (TANH - 65)) | (1L << (TRIM - 65)) | (1L << (TRUE - 65)) | (1L << (USING - 65)) | (1L << (VALUE - 65)) | (1L << (VALUES - 65)) | (1L << (WHERE - 65)) | (1L << (XOR - 65)) | (1L << (IMGCRSDOMAIN - 65)) | (1L << (DIV - 65)) | (1L << (METADATA - 65)) | (1L << (MOD - 65)) | (1L << (ID - 65)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3x\u00fb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\5\3?\n\3\3\4"+
		"\3\4\3\4\3\4\5\4E\n\4\3\5\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6"+
		"\7\6R\n\6\f\6\16\6U\13\6\3\6\5\6X\n\6\3\7\3\7\3\7\5\7]\n\7\5\7_\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bi\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f{\n\f\3\r\3\r\3\r\3\r\3\r\7\r\u0082"+
		"\n\r\f\r\16\r\u0085\13\r\5\r\u0087\n\r\3\r\3\r\3\16\3\16\3\16\5\16\u008e"+
		"\n\16\3\16\3\16\3\16\5\16\u0093\n\16\3\17\3\17\3\17\3\17\5\17\u0099\n"+
		"\17\5\17\u009b\n\17\3\20\3\20\7\20\u009f\n\20\f\20\16\20\u00a2\13\20\3"+
		"\21\3\21\3\21\7\21\u00a7\n\21\f\21\16\21\u00aa\13\21\3\22\3\22\3\22\7"+
		"\22\u00af\n\22\f\22\16\22\u00b2\13\22\3\23\3\23\3\23\7\23\u00b7\n\23\f"+
		"\23\16\23\u00ba\13\23\3\24\3\24\3\24\7\24\u00bf\n\24\f\24\16\24\u00c2"+
		"\13\24\3\25\3\25\3\25\7\25\u00c7\n\25\f\25\16\25\u00ca\13\25\3\26\3\26"+
		"\3\26\5\26\u00cf\n\26\3\26\3\26\3\26\5\26\u00d4\n\26\5\26\u00d6\n\26\3"+
		"\27\7\27\u00d9\n\27\f\27\16\27\u00dc\13\27\3\27\3\27\3\30\3\30\3\30\5"+
		"\30\u00e3\n\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\5\33\u00f0\n\33\3\34\3\34\3\34\3\34\3\34\5\34\u00f7\n\34\3\35\3\35\3"+
		"\35\2\2\36\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8\2\13\4\2\3\3\35\35\4\2aaxx\3\2\36\37\4\2!!;;\4\2%&\64\65\4\288BB\5\2"+
		"99jjll\4\2jjll\25\2\6\17\23\27\31\32\34\34  \"$\'\'--\61\63\66\67::=?"+
		"CFJJLQS[ggjlrr\2\u0102\2:\3\2\2\2\4>\3\2\2\2\6D\3\2\2\2\bF\3\2\2\2\nW"+
		"\3\2\2\2\f^\3\2\2\2\16h\3\2\2\2\20j\3\2\2\2\22n\3\2\2\2\24p\3\2\2\2\26"+
		"z\3\2\2\2\30|\3\2\2\2\32\u0092\3\2\2\2\34\u009a\3\2\2\2\36\u009c\3\2\2"+
		"\2 \u00a3\3\2\2\2\"\u00ab\3\2\2\2$\u00b3\3\2\2\2&\u00bb\3\2\2\2(\u00c3"+
		"\3\2\2\2*\u00d5\3\2\2\2,\u00da\3\2\2\2.\u00df\3\2\2\2\60\u00e4\3\2\2\2"+
		"\62\u00e6\3\2\2\2\64\u00ef\3\2\2\2\66\u00f6\3\2\2\28\u00f8\3\2\2\2:;\5"+
		"\24\13\2;\3\3\2\2\2<?\5\b\5\2=?\5\6\4\2><\3\2\2\2>=\3\2\2\2?\5\3\2\2\2"+
		"@A\7\35\2\2AE\5\b\5\2BC\7\3\2\2CE\5\b\5\2D@\3\2\2\2DB\3\2\2\2E\7\3\2\2"+
		"\2FK\5\n\6\2GH\t\2\2\2HJ\5\n\6\2IG\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2"+
		"\2L\t\3\2\2\2MK\3\2\2\2NO\5\f\7\2OS\5\16\b\2PR\5\20\t\2QP\3\2\2\2RU\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2TX\3\2\2\2US\3\2\2\2VX\5\22\n\2WN\3\2\2\2WV"+
		"\3\2\2\2X\13\3\2\2\2YZ\7d\2\2Z_\7p\2\2[]\7n\2\2\\[\3\2\2\2\\]\3\2\2\2"+
		"]_\3\2\2\2^Y\3\2\2\2^\\\3\2\2\2_\r\3\2\2\2`i\5\64\33\2ab\7e\2\2bc\7\60"+
		"\2\2ci\7I\2\2de\7\4\2\2ef\7\60\2\2fg\t\3\2\2gi\7I\2\2h`\3\2\2\2ha\3\2"+
		"\2\2hd\3\2\2\2i\17\3\2\2\2jk\7/\2\2kl\5\24\13\2lm\7H\2\2m\21\3\2\2\2n"+
		"o\t\4\2\2o\23\3\2\2\2pq\5 \21\2q\25\3\2\2\2r{\5\62\32\2st\7\60\2\2tu\5"+
		"\24\13\2uv\7I\2\2v{\3\2\2\2w{\t\3\2\2x{\7\\\2\2y{\5\30\r\2zr\3\2\2\2z"+
		"s\3\2\2\2zw\3\2\2\2zx\3\2\2\2zy\3\2\2\2{\27\3\2\2\2|}\5\60\31\2}\u0086"+
		"\7\60\2\2~\u0083\5\24\13\2\177\u0080\7\22\2\2\u0080\u0082\5\24\13\2\u0081"+
		"\177\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2"+
		"\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0086~\3\2\2\2\u0086\u0087"+
		"\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\7I\2\2\u0089\31\3\2\2\2\u008a"+
		"\u008d\5\34\17\2\u008b\u008c\7\5\2\2\u008c\u008e\5\32\16\2\u008d\u008b"+
		"\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0093\3\2\2\2\u008f\u0090\7\35\2\2"+
		"\u0090\u0091\7\5\2\2\u0091\u0093\5\32\16\2\u0092\u008a\3\2\2\2\u0092\u008f"+
		"\3\2\2\2\u0093\33\3\2\2\2\u0094\u009b\5\4\3\2\u0095\u0098\5\36\20\2\u0096"+
		"\u0097\t\2\2\2\u0097\u0099\5\b\5\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009b\3\2\2\2\u009a\u0094\3\2\2\2\u009a\u0095\3\2\2\2\u009b"+
		"\35\3\2\2\2\u009c\u00a0\5\26\f\2\u009d\u009f\5\20\t\2\u009e\u009d\3\2"+
		"\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\37\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a8\5\"\22\2\u00a4\u00a5\7=\2"+
		"\2\u00a5\u00a7\5\"\22\2\u00a6\u00a4\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9!\3\2\2\2\u00aa\u00a8\3\2\2\2"+
		"\u00ab\u00b0\5$\23\2\u00ac\u00ad\7\n\2\2\u00ad\u00af\5$\23\2\u00ae\u00ac"+
		"\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"#\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b8\5&\24\2\u00b4\u00b5\t\5\2\2"+
		"\u00b5\u00b7\5&\24\2\u00b6\u00b4\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6"+
		"\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9%\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb"+
		"\u00c0\5(\25\2\u00bc\u00bd\t\6\2\2\u00bd\u00bf\5(\25\2\u00be\u00bc\3\2"+
		"\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\'\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c8\5*\26\2\u00c4\u00c5\t\7\2\2"+
		"\u00c5\u00c7\5*\26\2\u00c6\u00c4\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6"+
		"\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9)\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb"+
		"\u00ce\5,\27\2\u00cc\u00cd\t\b\2\2\u00cd\u00cf\5*\26\2\u00ce\u00cc\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d6\3\2\2\2\u00d0\u00d3\7\35\2\2\u00d1"+
		"\u00d2\t\t\2\2\u00d2\u00d4\5*\26\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00cb\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d6"+
		"+\3\2\2\2\u00d7\u00d9\78\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da"+
		"\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2"+
		"\2\2\u00dd\u00de\5\32\16\2\u00de-\3\2\2\2\u00df\u00e2\5\66\34\2\u00e0"+
		"\u00e1\7\21\2\2\u00e1\u00e3\5\66\34\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3/\3\2\2\2\u00e4\u00e5\5.\30\2\u00e5\61\3\2\2\2\u00e6\u00e7"+
		"\7\30\2\2\u00e7\u00e8\5.\30\2\u00e8\63\3\2\2\2\u00e9\u00f0\79\2\2\u00ea"+
		"\u00eb\5\66\34\2\u00eb\u00ec\7\21\2\2\u00ec\u00ed\79\2\2\u00ed\u00f0\3"+
		"\2\2\2\u00ee\u00f0\5.\30\2\u00ef\u00e9\3\2\2\2\u00ef\u00ea\3\2\2\2\u00ef"+
		"\u00ee\3\2\2\2\u00f0\65\3\2\2\2\u00f1\u00f7\7f\2\2\u00f2\u00f7\7i\2\2"+
		"\u00f3\u00f7\7h\2\2\u00f4\u00f7\7d\2\2\u00f5\u00f7\58\35\2\u00f6\u00f1"+
		"\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f6\u00f3\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\67\3\2\2\2\u00f8\u00f9\t\n\2\2\u00f99\3\2\2\2\36"+
		">DKSW\\^hz\u0083\u0086\u008d\u0092\u0098\u009a\u00a0\u00a8\u00b0\u00b8"+
		"\u00c0\u00c8\u00ce\u00d3\u00d5\u00da\u00e2\u00ef\u00f6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}