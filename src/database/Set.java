package database;

public class Set<E extends Dto> {
	private DBInfo information;
	private boolean isSecond = false;

	public Set(DBInfo information) {
		super();
		this.information = information;
		CreateQueryMethod.appendQuery(information.getQuery(), "set");
	}

	public Set<E> set(String column, Object value) {
		if (isSecond) {
			CreateQueryMethod.appendQuery(information.getQuery(), ",");
		}
		CreateQueryMethod.appendQuery(information.getQuery(), column, Operator.EQUAL.toString());
		CreateQueryMethod.setValueClassCast(information.getQuery(), value);
		isSecond = true;
		return this;
	}

	public Where<E> where(String column, Operator operator, Object value) {
		return new Where<E>(information).where(column, operator, value);
	}
}
