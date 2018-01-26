package gr.cite.earthserver.xwcpsmars.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSLexer;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser;
import gr.cite.earthserver.xwcpsmars.mars.MarsRequest;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XWCPSEvalVisitor;
import gr.cite.earthserver.xwcpsmars.parser.visitors.XwcpsErrorListener;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class XwcpsParserTest {
	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void query() throws JsonProcessingException {
		//String wcpsQuery = "for c in (temp_2m_test) return encode(c[Lat(53.0),Long(-1.0),ansi(\"2015-12-31T00:00\")]-273.15,\"csv\")";
		//String wcpsQuery = "for c in precipitation return encode(add(c[Lat(50.52:50.52), Long(-4.22:-4.22), ansi(\"1979-01-01T00:00\")] * 1000), \"csv\") ";
		//String wcpsQuery = "for c in precipitation return encode(add(c[Lat(50.52:50.52), Long(-4.22:-4.22),ansi(\"1979-01-01T00:00\" :\"2015-12-31T18:00\")] * 1000), \"csv\") ";
		String wcpsQuery = "for c in (temp2m) return encode(c[Lat(53.0), Long(-1.0)] - 273.15, csv)";
		//String wcpsQuery = "for c in (temp_2m_test) return encode(c[Lat(53.0:52.0), Long(1.0:-1.0),ansi(\"2015-12-31T18:00\")] - 273.15, \"csv\")";

		CharStream stream = CharStreams.fromString(wcpsQuery);
		XWCPSLexer lexer = new XWCPSLexer(stream);
		XWCPSParser parser = new XWCPSParser(new CommonTokenStream(lexer));

		List<String > errors = new ArrayList<>();
		ANTLRErrorListener errorListener = new XwcpsErrorListener(errors);
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);

		ParseTree tree = parser.xwcps();

		XWCPSEvalVisitor visitor = new XWCPSEvalVisitor();
		MarsRequest.MarsRequestBuilder marsRequestBuilder = visitor.visit(tree);

		errors.forEach(System.out::println);

		String coverageId = visitor.getCoverageId();
		//System.out.println(mapper.writeValueAsString(marsRequestBuilder.build()));

	}
}
