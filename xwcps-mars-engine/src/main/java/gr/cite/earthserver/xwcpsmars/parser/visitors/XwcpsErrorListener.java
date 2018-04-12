package gr.cite.earthserver.xwcpsmars.parser.visitors;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class XwcpsErrorListener implements ANTLRErrorListener {
	private List<String> errorMessages;

	public XwcpsErrorListener(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		String sourceName = recognizer.getInputStream().getSourceName();
		if (!sourceName.isEmpty()) {
			sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
		}

		String errorMessage = sourceName + "line "+ line + ":" + charPositionInLine + " " + msg;
		//System.err.println(errorMessage);
		this.errorMessages.add(errorMessage);
	}

	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact, BitSet ambigAlts, ATNConfigSet configs) {

	}

	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex, BitSet conflictingAlts, ATNConfigSet configs) {

	}

	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction, ATNConfigSet configs) {

	}
}
