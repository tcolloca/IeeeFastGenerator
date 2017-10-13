
public enum Type {

	INT, DOUBLE, FLOAT, STRING, BOOLEAN, CHAR, LONG, LINE, BIGINTEGER, BIGDECIMAL;
	
	public static Type get(String name) {
		for (Type type : Type.values()) {
			if (type.name().toUpperCase().startsWith(name.toUpperCase())) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknown type: " + name);
	}
	
	public String toTypeString () {
		if (this.equals(STRING) || this.equals(LINE)) {
			return "String";
		}
		if (this.equals(BIGINTEGER)) {
			return "BigInteger";
		}
		if (this.equals(BIGDECIMAL)) {
			return "BigDecimal";
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
		if (this.equals(BIGINTEGER)) {
			return "sc.nextBigInteger()";
		}
		if (this.equals(BIGDECIMAL)) {
			return "sc.nextBigDecimal()";
		}
		String name = this.name().toLowerCase();
		return "sc.next" + Character.toUpperCase(name.charAt(0)) + name.substring(1) + "()";
	}
}
