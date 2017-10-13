
public enum Type {

	INT, DOUBLE, FLOAT, STRING, BOOLEAN, CHAR, LONG;
	
	public static Type get(String name) {
		for (Type type : Type.values()) {
			if (name.toUpperCase().equals(type.name().toUpperCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown type: " + name);
	}
	
	public String toTypeString () {
		if (this.equals(STRING)) {
			return "String";
		}
		return this.name().toLowerCase();
	}
	
	public String toScannerStr() {
		if (this.equals(STRING)) {
			return "sc.next()";
		}
		if (this.equals(CHAR)) {
			return "sc.next().charAt(0)";
		}
		String name = this.name().toLowerCase();
		return "sc.next" + Character.toUpperCase(name.charAt(0)) + name.substring(1) + "()";
	}
}
