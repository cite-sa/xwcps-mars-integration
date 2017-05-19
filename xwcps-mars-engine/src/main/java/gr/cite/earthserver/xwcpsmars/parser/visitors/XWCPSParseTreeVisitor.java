package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.XwcpsContext;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XWCPSParseTreeVisitor extends XWCPSBaseVisitor<MarsRequest> {
	private static final Logger logger = LoggerFactory.getLogger(XWCPSParseTreeVisitor.class);

	/*@Override
	public abstract MarsRequest visitTerminal(TerminalNode node);*/

	@Override
	public abstract MarsRequest visitXwcps(XwcpsContext ctx);

}
