package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Where extends ExecuteQuery {
	private Dto table;
	private StringBuilder query;
	private boolean isSecond = false;
	private ArrayList<String> colums;

	public Where(StringBuilder query, Dto table, ArrayList<String> colums) {
		this.query = query;
		this.table = table;
		this.colums = colums;
	}

	public Where where(String column, Operator operator, Object value) {
		if (isSecond) {
			return where(LogicalOperator.AND, column, operator, value);
		}
		query.append(" where ");
		setWhere(column, operator, value);
		isSecond = true;
		return this;
	}

	public Where where(LogicalOperator logicalOperator, String column, Operator operator,
			Object value) {
		query.append(" " + logicalOperator.name() + " ");
		setWhere(column, operator, value);
		return this;
	}

	public ArrayList<Dto> executeQuery() {
		return executeQuery(query, table, colums);
	}

	/**
	 * where句の条件部分を返すメソッド 対応しているクラス java.lang.String java.sql.Date
	 * java.util.Date java.util.Calendar 今後対応したいクラス 時間 例）「id = "10"」
	 *
	 * @param column
	 *            idの部分
	 * @param operator
	 *            =の部分
	 * @param value
	 *            "10"の部分
	 * @return where句の条件部分のString（id = "10"）
	 *
	 */
	private void setWhere(String column, Operator operator, Object value) {
		String classStr = value.getClass().getName();
		System.out.println(classStr);
		query.append(" " + column + " " + operator.toString() + " ");
		switch (classStr) {
		case "java.lang.String":
		case "java.sql.Date":
			setStringColumns(query, value.toString());
			break;
		case "java.lang.Integer":
			query.append(" " + value.toString() + " ");
			break;
		case "java.util.Date":
			setStringColumns(query, value.toString());
			break;
		case "java.util.Calendar":
			query.append(new SimpleDateFormat("yyyy-mm-dd").format((Calendar) value));
			break;
		default:
			System.out.println("unecpected class : " + value.toString());
			break;
		}
	}

	private static void setStringColumns(StringBuilder query, String value) {
		query.append(" \"" + value + "\" ");
	}
}
