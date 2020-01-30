package database;

public class CreateQueryMethod {

	public static void appendQuery(StringBuilder query, String... values) {
		for (String value : values) {
			query.append(" " + value + " ");
		}
	}

	public static void setValuesWithComma(StringBuilder query, String[] values) {
		for (String value : values) {
			query.append(" " + value + " , ");
		}
		query.delete(query.length() - 2, query.length());
	}

	public static void setQuestionMark(StringBuilder query, int num) {
		while (num-- > 0) {
			query.append(" ? , ");
		}
		query.delete(query.length() - 2, query.length());
	}
}
