import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	public static List<VariableList> parse(List<String> lines) throws IOException {
		int prevBlockCount = 0;
		int childBlockCount = -1;
		Variable prevVar = null;
		Variable parentVar = null;
		List<VariableList> topVars = new ArrayList<>();
		for (String line : lines) {
			int blockCount = line.lastIndexOf("\t") + 1;
			System.out.println(blockCount);
			line = line.trim();
			Pattern p = Pattern.compile("\\(([a-zA-Z0-9_ ,-]+)\\)\\*([a-zA-Z0-9_-]+)");
			Matcher m = p.matcher(line);
			boolean matches = m.find();
			VariableList varList;
			if (matches) {
				String varsStr = m.group(1).trim();
				String amountVar = m.group(2).trim();
				varList = new VariableList(getVars(varsStr), amountVar);
			} else {
				varList = new VariableList(getVars(line));
			}
			if (blockCount > prevBlockCount) {
				parentVar = prevVar;
				childBlockCount = blockCount;
			}
			if (blockCount == childBlockCount) {
				parentVar.addChild(varList);
			}
			if (blockCount == 0) {
				topVars.add(varList);
			}
			prevBlockCount = blockCount;
			prevVar = varList.getFirst();
		}
		topVars.stream().forEach(line -> System.out.println(line));
		return topVars;
	}
	
	private static List<Variable> getVars(String str) {
		String[] vars = str.split(",");
		List<Variable> variables = new ArrayList<>();
		for (String var : vars) {
			String[] varArgs = var.trim().split(" ");
			String varType = varArgs[0].trim();
			String varName = varArgs[1].trim();
			Variable variable = new Variable(varType, varName);
			variables.add(variable);
		}
		return variables;
	}
}
