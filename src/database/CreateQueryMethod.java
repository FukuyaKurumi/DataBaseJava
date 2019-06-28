package database;

public interface CreateQueryMethod {
	default void setStringColumns(StringBuilder query, String... values) {
		for (String value : values) {
			setColumn(query, "\"" + value + "\"");
		}
	}

	default void setColumns(StringBuilder query, String... values) {
		for (String value : values) {
			setColumn(query, value);
		}
	}

	default void setColumn(StringBuilder query, String value) {
		query.append(" " + value + " ");
	}
}
