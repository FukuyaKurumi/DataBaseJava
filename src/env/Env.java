package env;

public enum Env {
	DRIVER("com.mysql.cj.jdbc.Driver"),
	DATABASE("database_access_test"),
	HOST("localhost"),
	PORT("3306"),
	DIR("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?serverTimezone=JST"),
	UPDATE_USER("user"),
	UPDATE_PASS("password"),
	REFERENCE_USER("user"),
	REFERENCE_PASS("password");

	private String value;

	private Env(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
}
