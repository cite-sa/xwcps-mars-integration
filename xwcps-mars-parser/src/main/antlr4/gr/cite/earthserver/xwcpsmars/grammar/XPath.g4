grammar XPath;

import WCPSLexerTokens;

main  :  expr
  ;

locationPath 
  :  relativeLocationPath
  |  absoluteLocationPathNoroot
  ;

absoluteLocationPathNoroot
  :  '/' relativeLocationPath
  |  '//' relativeLocationPath
  ;

relativeLocationPath
  :  step (('/'|'//') step)*
  ;

step  :  axisSpecifier nodeTest predicate*
  |  abbreviatedStep
  ;

axisSpecifier
  :  AxisNameXpath '::'
  |  '@'?
  ;

nodeTest:  nameTest
  |  NodeType LEFT_PARENTHESIS RIGHT_PARENTHESIS
  |  'processing-instruction' LEFT_PARENTHESIS ( XPATH_LITERAL | STRING_LITERAL) RIGHT_PARENTHESIS
  ;

predicate
  :  LEFT_BRACKET expr RIGHT_BRACKET
  ;

abbreviatedStep
  :  DOT
  |  DOTDOT
  ;

expr  :  orExpr
  ;

primaryExpr
  :  variableReference
  |  LEFT_PARENTHESIS expr RIGHT_PARENTHESIS
  |  ( XPATH_LITERAL | STRING_LITERAL)
  |  REAL_NUMBER_CONSTANT
  |  functionCall
  ;

functionCall
  :  functionName LEFT_PARENTHESIS ( expr ( ',' expr )* )? RIGHT_PARENTHESIS
  ;

unionExprNoRoot
  :  pathExprNoRoot ('|' unionExprNoRoot)?
  |  '/' '|' unionExprNoRoot
  ;

pathExprNoRoot
  :  locationPath
  |  filterExpr (('/'|'//') relativeLocationPath)?
  ;

filterExpr
  :  primaryExpr predicate*
  ;

orExpr  :  andExpr (OR andExpr)*
  ;

andExpr  :  equalityExpr (AND equalityExpr)*
  ;

equalityExpr
  :  relationalExpr (('='|'!=') relationalExpr)*
  ;

relationalExpr
  :  additiveExpr ((LOWER_THAN | GREATER_THAN | LOWER_OR_EQUAL_THAN | GREATER_OR_EQUAL_THAN) additiveExpr)*
  ;

additiveExpr
  :  multiplicativeExpr (('+'|'-') multiplicativeExpr)*
  ;

multiplicativeExpr
  :  unaryExprNoRoot (( '*' | DIV | MOD ) multiplicativeExpr)?
  |  '/' (( DIV | MOD ) multiplicativeExpr)?
  ;

unaryExprNoRoot
  :  '-'* unionExprNoRoot
  ;

qName  :  nCName (':' nCName)?
  ;

functionName
  :  qName  // Does not match nodeType, as per spec.
  ;

variableReference
  :  '$' qName
  ;

nameTest:  '*'
  |  nCName ':' '*'
  |  qName
  ;

nCName  :  NCName | SIMPLE_IDENTIFIER_WITH_NUMBERS | SIMPLE_IDENTIFIER
  |  AxisNameXpath
  |  wcpsHotWords
  ;

wcpsHotWords:  FOR
	|  ABSOLUTE_VALUE
	|  ADD
	|  ALL
	|  AND
	|  ARCSIN
	|  ARCCOS
	|  ARCTAN
	|  AVG
	|  BIT
	|  CONDENSE
	|  COS
	|  COSH
	|  COUNT
	|  COVERAGE
	|  CRS_TRANSFORM
	|  DECODE
	|  DESCRIBE_COVERAGE
	|  DIV
	|  ENCODE
	|  EXP
	|  EXTEND
	|  FALSE 
	|  IMAGINARY_PART
	|  ID
	|  IMGCRSDOMAIN
	|  IN
	|  LN
	|  LIST
	|  LOG
	|  MAX
	|  MIN
	|  METADATA
	|  MOD
	|  NOT
	|  OR
	|  OVER
	|  OVERLAY
	|  POWER
	|  REAL_PART
	|  ROUND
	|  RETURN
	|  SCALE
	|  SIN
	|  SINH
	|  SLICE
	|  SOME
	|  SQUARE_ROOT
	|  STRUCT
	|  TAN
	|  TANH
	|  TRIM
	|  TRUE
	|  USING
	|  VALUE
	|  VALUES
	|  WHERE
	|  XOR
	;
