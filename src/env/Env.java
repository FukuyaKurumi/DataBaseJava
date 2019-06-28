package env;

public enum Env {
	DRIVER("com.mysql.cj.jdbc.Driver"),
	DATABASE("database_access_test"),
	HOST("localhost"),
	PORT("3306"),
	DIR("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?serverTimezone=JST"),
	USER("user"),
	PASS("password");

	private String value;

	private Env(String value) {
		this.value = value;
	}

	public String toString() {
		return value;
	}
	/*
	 * Arraylist<User> users ( new
	 * DB("user").select("id","name").where("id",Operator.EQUAL,"29");
	 * Arraylist<User> users ( new
	 * DB("user").select("id","name").where("name",Operator.LIKE,"%大%");
	 */
}
