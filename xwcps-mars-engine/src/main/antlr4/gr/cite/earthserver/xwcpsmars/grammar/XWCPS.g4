grammar XWCPS;

import WCPS, XPath, WCPSLexerTokens;

xwcps : (letClause)* wcpsQuery
	| xpath
	; 
	
xpath: main;

orderByClause: ORDERBY xpathClause (ASC|DESC)?; 

letClause: LET COVERAGE_VARIABLE_NAME ':=' letClauseExpression;

letClauseExpression : arithmeticExpression 
					| processingExpression // TODO can I remove this from here?
					;

/**
 * Example: 
 * for c in ( AvgLandTemp ) return <a>describeCoverage(c)//*[local-name()='domainSet']</a>
 */
xmlClause: openXmlElement xmlPayload closeXmlElement
				| openXmlElement (quated)? (xmlClauseWithQuate)* closeXmlElement 
				| (openXmlWithClose) + 
				;

xmlPayload: 
		  arithmeticExpression
		;

arithmeticExpression :  
				  arithmeticExpression booleanOperator arithmeticExpression
				| arithmeticExpression coverageArithmeticOperator arithmeticExpression
				| arithmeticExpression numericalComparissonOperator arithmeticExpression
				| LEFT_PARENTHESIS arithmeticExpression RIGHT_PARENTHESIS
				| xpathClause
				| coverageExpression
				;

xmlClauseWithQuate: xmlClause (quated)?;

openXmlElement: xmlElement GREATER_THAN; 

openXmlWithClose: xmlElement GREATER_THAN_SLASH;

xmlElement: LOWER_THAN (qName) (attribute)*;

attribute: qName EQUAL quated 
		 | qName EQUAL xpathClause
		 ;

quated: ( XPATH_LITERAL | STRING_LITERAL);
			
closeXmlElement: LOWER_THAN_SLASH qName GREATER_THAN;

/**
 * Example: 
 * for c in ( AvgLandTemp ) return describeCoverage(c)//*[local-name()='domainSet']
 * for c in ( AvgLandTemp ) return min(describeCoverage(c)//*[local-name()='domainSet']//@anyattr)
 */
xpathClause: metadataExpression (xpath)?
			| scalarExpression (xpath)?
			| functionName LEFT_PARENTHESIS scalarExpression xpath RIGHT_PARENTHESIS;
			
wrapResultClause: WRAP_RESULT LEFT_PARENTHESIS
					processingExpression COMMA  openXmlElement ( wrapResultSubElement )*
					RIGHT_PARENTHESIS;
					
wrapResultSubElement: openXmlElement | xmlClause ;

xpathForClause:  coverageVariableName IN xwcpsCoveragesClause;

xwcpsCoveragesClause: xpath;

mixedClause: MIXED LEFT_PARENTHESIS encodedCoverageExpression COMMA (xmlClause | xpathClause) RIGHT_PARENTHESIS;

//metadataClause: METADATA LEFT_PARANTHESIS coverageVariableName RIGHT_PARANTHESIS;

metadataExpression: coverageVariableName DOUBLE_COLON;

//////overrided wcps rules//////

whereClause: WHERE (booleanScalarExpression | booleanXpathClause );

booleanXpathClause : xpathClause;

// on return
processingExpression: 
 					xmlClause
					| xpathClause
					| wrapResultClause
                    | encodedCoverageExpression
                    | mixedClause
                    ;

wcpsQuery : (forClauseList) (letClause)* (whereClause)? (orderByClause)? (returnClause);

forClauseList: FOR (xwcpsforClause) (COMMA xwcpsforClause)*;

xwcpsforClause: forClause
			| xpathForClause
			;
			
endpointIdentifier: COVERAGE_VARIABLE_NAME | STRING_LITERAL | XPATH_LITERAL;
						
extendedIdentifier: COVERAGE_VARIABLE_NAME (AT) endpointIdentifier			#specificIdInServerLabel
			| (MULTIPLICATION) (AT) endpointIdentifier			#allCoveragesInServerLabel
			| (MULTIPLICATION)									#allCoveragesLabel
			| COVERAGE_VARIABLE_NAME										#specificIdLabel
			;
			
forClause:  coverageVariableName IN
           (LEFT_PARENTHESIS)? (extendedIdentifier) (COMMA (extendedIdentifier))* (RIGHT_PARENTHESIS)?;
