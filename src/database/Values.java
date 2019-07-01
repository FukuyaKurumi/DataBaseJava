package database;

public class Values<E extends Dto> extends ExecuteUpdate<E> implements CreateQueryMethod {
	private DBInfo information;

	public Values(DBInfo information) {
		this.information = information;
	}

	public ExecuteUpdate<E> values(String... values) {
		appendQuery(information.getQuery(), "values", "(");
		setValuesWithComma(information.getQuery(), values);
		appendQuery(information.getQuery(), ")");
		return new ExecuteUpdate<E>();
	}
}
