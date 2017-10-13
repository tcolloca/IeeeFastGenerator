import java.util.List;

public class VariableList {

	private List<Variable> vars;
	private String amountVar;

	public VariableList(List<Variable> vars) {
		super();
		this.vars = vars;
	}

	public VariableList(List<Variable> vars, String amountVar) {
		super();
		this.vars = vars;
		this.amountVar = amountVar;
	}
	
	public List<Variable> getVars() {
		return vars;
	}

	public String getAmountVar() {
		return amountVar;
	}

	@Override
	public String toString() {
		return "VariableList [vars=" + vars + ", amountVar=" + amountVar + "]";
	}

	public Variable getFirst() {
		return vars.get(0);
	}
}
