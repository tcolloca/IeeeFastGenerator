import java.util.ArrayList;
import java.util.List;

public class Variable {

	private final Type type;
	private final String name;
	private final List<VariableList> children;
	
	public Variable(String type, String name) {
		this.type = Type.get(type);
		this.name = name;
		this.children = new ArrayList<>();
	}

	public void addChild(VariableList varList) {
		children.add(varList);
	}

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public List<VariableList> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "Variable [type=" + type + ", name=" + name + ", children=" + children + "]";
	}
}
