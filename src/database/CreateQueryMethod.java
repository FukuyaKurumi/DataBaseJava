package database;

public interface CreateQueryMethod {
	default void appendQueryString(StringBuilder query, String... values) {
		for (String value : values) {
			appendValue(query, "\"" + value + "\"");
		}
	}

	default void appendQuery(StringBuilder query, String... values) {
		for (String value : values) {
			appendValue(query, value);
		}
	}

	default void appendValue(StringBuilder query, String value) {
		query.append(" " + value + " ");
	}

	default void setValuesWithComma(StringBuilder query, String[] values) {
		for (String value : values) {
			appendQuery(query, value, ",");
		}
		query.delete(query.length() - 2, query.length());
	}
}
