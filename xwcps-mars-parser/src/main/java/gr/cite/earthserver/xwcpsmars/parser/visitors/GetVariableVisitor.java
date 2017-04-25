package gr.cite.earthserver.xwcpsmars.parser.visitors;

import gr.cite.earthserver.xwcpsmars.grammar.XWCPSBaseVisitor;
import gr.cite.earthserver.xwcpsmars.grammar.XWCPSParser.CoverageVariableNameLabelContext;

import java.util.HashSet;
import java.util.Set;

public class GetVariableVisitor extends XWCPSBaseVisitor<Void> {

	private Set<String> variables = new HashSet<>();

	@Override
	public Void visitCoverageVariableNameLabel(CoverageVariableNameLabelContext ctx) {

		variables.add(ctx.getText());

		return null;
	}

	public Set<String> getVariables() {
		return variables;
	}
}
