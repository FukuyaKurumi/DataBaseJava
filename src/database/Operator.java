package database;

public enum Operator {
	LIKE(" like "), EQUAL(" = ");
	private String value;

	private Operator(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
