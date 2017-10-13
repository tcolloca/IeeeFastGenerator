import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;

public class Main {

	private static int INDEX = 0;
	
	public static void main(String[] args) throws IOException {
		
		List<String> lines = Files.readAllLines(Paths.get("data.txt"));
		String path = lines.get(0).split("path")[1].trim().split("=")[1].trim();
		System.out.println(path);
		lines.remove(0);
		String pckg = lines.get(0).split("pckg")[1].trim().split("=")[1].trim();
		System.out.println(pckg);
		lines.remove(0);
		String name = lines.get(0).split("name")[1].trim().split("=")[1].trim();
		System.out.println(name);
		lines.remove(0);
		
		List<VariableList> varList = Parser.parse(lines);
		
		Builder mainBuilder = MethodSpec.methodBuilder("main")
			    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
			    .returns(void.class)
			    .addParameter(String[].class, "args")
			    .addStatement("$T ans = new $T()", StringBuilder.class, StringBuilder.class)
			    .addStatement("$T sc = new $T($T.in)", Scanner.class, Scanner.class, System.class);
		
		mainBuilder = writeCode(varList, mainBuilder);

		TypeSpec classInfo = TypeSpec.classBuilder(name).addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(mainBuilder.build()).build();

			JavaFile javaFile = JavaFile.builder(pckg, classInfo)
			    .build();

		javaFile.writeTo(Paths.get(path));
	}
	
	public static Builder writeCode(List<VariableList> varLists, Builder builder) {
		for (VariableList varList : varLists) {
			String amountVar = varList.getAmountVar();
			if (amountVar != null) {
				for (Variable var : varList.getVars()) {
					Type type = var.getType();
					String name = var.getName();
					builder.addStatement(convertToArrayStmt(type, name, amountVar));
				}
				String indexName = getForLine(builder, amountVar);
				for (Variable var : varList.getVars()) {
					Type type = var.getType();
					String name = var.getName();
					builder.addStatement(convertToAddToArrayStmt(type, name, indexName));
				}
				builder.addCode("}\n");
			} else {
				for (Variable var : varList.getVars()) {
					Type type = var.getType();
					String name = var.getName();
					builder.addStatement(convertToStmt(type, name));
					if (!var.getChildren().isEmpty()) {
						getForLine(builder, name);
						writeCode(var.getChildren(), builder);
						builder.addCode("}\n");
					}
				}
			}			
		}
		return builder;
	}
	
	private static String getForLine(Builder builder, String name) {
		String indexName = "index_" + INDEX++;
		builder.addCode("for (int " + indexName + " = 0; " + indexName + " < " + name + "; " + indexName + "++) {\n");
		return indexName;
	}
	
	private static String convertToStmt(Type type, String name) {
		return type.toTypeString() + " " + name + " = " + type.toScannerStr();
	}
	
	private static String convertToArrayStmt(Type type, String name, String amountVar) {
		return type.toTypeString() + "[] " + name + "s = new " + type.toTypeString() + "[" + amountVar + "]";
	}
	
	private static String convertToAddToArrayStmt(Type type, String name, String indexName) {
		return name + "s[" + indexName + "] = " + type.toScannerStr();
	}
}
