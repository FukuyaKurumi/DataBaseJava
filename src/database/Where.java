package database;

import java.util.ArrayList;

public class Where<E extends Dto> {
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
		CreateQueryMethod.appendQuery(information.getQuery(), "where");
		setWhere(column, operator, value);
		isSecond = true;
		return this;
	}

	public Where<E> where(LogicalOperator logicalOperator, String column, Operator operator,
			Object value) {
		CreateQueryMethod.appendQuery(information.getQuery(), logicalOperator.name());
		setWhere(column, operator, value);
		return this;
	}

	public ArrayList<E> executeQuery() {
		return new ExecuteQuery<E>(information).executeQuery();
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
		information.getQuery().append(" " + column + " " + operator.toString() + " ");
		CreateQueryMethod.setValueClassCast(value, information.getQuery());
	}
}
