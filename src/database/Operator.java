package database;

public enum Operator {
	LIKE(" like "),
	EQUAL(" = "),
	GREATER(" > "),
	LESS(" < "),
	GREATER_EQUAL(" >= "),
	LESS_EQUPAL(" <= ");
	private String value;

	private Operator(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
