package gr.cite.earthserver.xwcpsmars.parser;

import gr.cite.earthserver.xwcpsmars.grammar.WCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.WCPSParser;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.request.MarsRequest;
import gr.cite.earthserver.xwcpsmars.parser.visitors.WCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

public class XwcpsParserTest {

	@Test
	public void query() {
		String wcpsQuery = "for c in precipitation return encode(add(c[Lat(50.52:50.52), Long(-4.22:-4.22),ansi" +
				"(\"19790101T00:00\" :\"20151231T18:00\")] * 1000), \"csv\") ";

		CharStream stream = CharStreams.fromString(wcpsQuery);
		XWCPSLexer lexer = new XWCPSLexer(stream);
		XWCPSParser parser = new XWCPSParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.xwcps();

		XWCPSEvalVisitor visitor = new XWCPSEvalVisitor();
		MarsRequest marsRequest = visitor.visit(tree);
		String coverageId = visitor.getCoverageId();
		System.out.println(marsRequest);
	}
}
