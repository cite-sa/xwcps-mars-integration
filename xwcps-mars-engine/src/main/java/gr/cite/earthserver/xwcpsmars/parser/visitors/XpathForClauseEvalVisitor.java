package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.AndExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.EqualityExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.MainContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.OrExprContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.RelativeLocationPathContext;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.StepContext;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XpathForClauseEvalVisitor extends XWCPSBaseVisitor<MarsRequest> {
	private static final Logger logger = LoggerFactory.getLogger(XpathForClauseEvalVisitor.class);

	public XpathForClauseEvalVisitor() {
		
	}

	@Override
	public MarsRequest visitMain(MainContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public MarsRequest visitRelativeLocationPath(RelativeLocationPathContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public MarsRequest visitOrExpr(OrExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public MarsRequest visitAndExpr(AndExprContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public MarsRequest visitStep(StepContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public MarsRequest visitEqualityExpr(EqualityExprContext ctx) {
		return visitChildren(ctx);
	}

	/*@Override
	protected MarsRequest aggregateResult(MarsRequest aggregate, MarsRequest nextResult) {
		return visitChildren(ctx);
	}*/
}