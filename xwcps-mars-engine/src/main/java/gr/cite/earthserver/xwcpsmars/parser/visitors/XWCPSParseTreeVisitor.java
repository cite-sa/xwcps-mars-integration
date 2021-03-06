package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.XwcpsContext;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest.MarsRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class XWCPSParseTreeVisitor extends XWCPSBaseVisitor<MarsRequestBuilder> {
	private static final Logger logger = LoggerFactory.getLogger(XWCPSParseTreeVisitor.class);

	/*@Override
	public abstract MarsRequest visitTerminal(TerminalNode node);*/

	@Override
	public abstract MarsRequestBuilder visitXwcps(XwcpsContext ctx);

}
