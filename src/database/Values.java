package database;

public class Values<E extends Dto> {
	private DBInfo information;

	public Values(DBInfo information) {
		this.information = information;
	}

	public ExecuteUpdate<E> values(Object... values) {
		System.out.println("start values : " + information.getQuery());
		CreateQueryMethod.appendQuery(information.getQuery(), "values", "(");
		CreateQueryMethod.setValueClassCastWithComma(information.getQuery(), values);
		CreateQueryMethod.appendQuery(information.getQuery(), ")");
		return new ExecuteUpdate<E>(information);
	}
}
