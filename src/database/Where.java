package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Where<E extends Dto> extends ExecuteQuery<E> implements CreateQueryMethod {
	private DBInfo information;
	private boolean isSecond;

	public Where(DBInfo infomation) {
		super();
		this.information = infomation;
		this.isSecond = false;
	}

	public Where<E> where(String column, Operator operator, Object value) {
		if (isSecond) {
			return where(LogicalOperator.AND, column, operator, value);
		}
		appendQuery(information.getQuery(), "where");
		setWhere(column, operator, value);
		isSecond = true;
		return this;
	}

	public Where<E> where(LogicalOperator logicalOperator, String column, Operator operator,
			Object value) {
		appendQuery(information.getQuery(), logicalOperator.name());
		setWhere(column, operator, value);
		return this;
	}

	public ArrayList<E> executeQuery() {
		return executeQuery(information);
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

		information.getQuery().append(" " + column + " " + operator.toString() + " ");

		switch (classStr) {
		case "java.lang.String":
		case "java.sql.Date":
			appendQueryString(information.getQuery(), value.toString());
			break;
		case "java.lang.Integer":
			appendQuery(information.getQuery(), value.toString());
			break;
		case "java.util.Date":
			appendQueryString(information.getQuery(), value.toString());
			break;
		case "java.util.Calendar":
			information.getQuery()
					.append(new SimpleDateFormat("yyyy-mm-dd").format((Calendar) value));
			break;
		default:
			System.out.println("unecpected class : " + value.toString());
			break;
		}
	}
}
