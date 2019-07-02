package database;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateQueryMethod {
	public static void appendQueryString(StringBuilder query, String... values) {
		for (String value : values) {
			appendValue(query, "\"" + value + "\"");
		}
	}

	public static void appendQuery(StringBuilder query, String... values) {
		for (String value : values) {
			appendValue(query, value);
		}
	}

	public static void appendValue(StringBuilder query, String value) {
		query.append(" " + value + " ");
	}

	public static void setValuesWithComma(StringBuilder query, String[] values) {
		for (String value : values) {
			appendQuery(query, value, ",");
		}
		query.delete(query.length() - 2, query.length());
	}

	public static void setValueClassCast(StringBuilder query, Object value) {
		String classStr = value.getClass().getName();
		System.out.println(classStr);
		switch (classStr) {
		case "java.lang.String":
		case "java.sql.Date":
			appendQueryString(query, value.toString());
			break;
		case "java.lang.Integer":
		case "java.lang.Boolean":
			appendQuery(query, value.toString());
			break;
		case "java.util.Date":
			appendQueryString(query, value.toString());
			break;
		case "java.util.Calendar":
			query.append(new SimpleDateFormat("yyyy-mm-dd").format((Calendar) value));
			break;
		default:
			System.out.println("unecpected class : " + value.toString());
			break;
		}
	}

	public static void setValueClassCastWithComma(StringBuilder query, Object... values) {
		for (Object value : values) {
			setValueClassCast(query, value);
			appendQuery(query, ",");
		}
		query.delete(query.length() - 2, query.length());
	}
}
