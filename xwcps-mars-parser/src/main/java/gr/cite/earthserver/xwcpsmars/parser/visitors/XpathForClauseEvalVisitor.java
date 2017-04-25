package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.AndExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.EqualityExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.MainContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.OrExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.RelativeLocationPathContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.StepContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class XpathForClauseEvalVisitor extends XWCPSBaseVisitor<String> {
	private static final Logger logger = LoggerFactory.getLogger(XpathForClauseEvalVisitor.class);

	public XpathForClauseEvalVisitor() {
		
	}

	@Override
	public String visitMain(MainContext ctx) {
		return null;
	}

	@Override
	public String visitRelativeLocationPath(RelativeLocationPathContext ctx) {
		return null;
	}

	@Override
	public String visitOrExpr(OrExprContext ctx) {
		return null;
	}

	@Override
	public String visitAndExpr(AndExprContext ctx) {
		return null;
	}

	@Override
	public String visitStep(StepContext ctx) {
		return null;
	}

	@Override
	public String visitEqualityExpr(EqualityExprContext ctx) {
		return null;
	}

	@Override
	protected String aggregateResult(String aggregate, String nextResult) {
		return null;
	}
}