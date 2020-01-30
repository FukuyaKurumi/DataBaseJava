package database;

public class Set<E extends Dto> {
	private DBInfo information;
	private StringBuilder query;
	private boolean isSecond = false;

	public Set(DBInfo information) {
		super();
		this.information = information;
		query = information.getQuery();
		CreateQueryMethod.appendQuery(query, "set");
	}

	public Set<E> set(String column, Object value) {
		if (isSecond) {
			CreateQueryMethod.appendQuery(query, ",");
		}
		CreateQueryMethod.appendQuery(query, column, Operator.EQUAL.toString());
		information.addStatement(value);
		CreateQueryMethod.setQuestionMark(query, 1);
		isSecond = true;
		return this;
	}

	public Where<E> where(String column, Operator operator, Object value) {
		return new Where<E>(information).where(column, operator, value);
	}
}
