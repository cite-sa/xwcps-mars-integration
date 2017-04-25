package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.XwcpsContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XWCPSParseTreeVisitor extends XWCPSBaseVisitor<String> {
	private static final Logger logger = LoggerFactory.getLogger(XWCPSParseTreeVisitor.class);

	@Override
	public String visitTerminal(TerminalNode node) {
		return null;
	}

	@Override
	public abstract String visitXwcps(XwcpsContext ctx);

}
