package database;

public class Values<E extends Dto> {
	private DBInfo information;
	private StringBuilder query;

	public Values(DBInfo information) {
		this.information = information;
		query = information.getQuery();
	}

	public ExecuteUpdate<E> values(Object... values) {
		CreateQueryMethod.appendQuery(query, "values", "(");
		CreateQueryMethod.setQuestionMark(query, values.length);
		for (Object object : values) {
			information.addStatement(object);
		}
		CreateQueryMethod.appendQuery(query, ")");
		return new ExecuteUpdate<E>(information);
	}
}
