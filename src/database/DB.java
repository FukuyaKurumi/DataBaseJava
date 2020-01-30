package database;

import java.util.ArrayList;

public class DB<E extends Dto> {
	DBInfo information;
	private ArrayList<String> columnNames;
	private StringBuilder query;
	private Dao<Dto> dao;

	@SuppressWarnings("unchecked")
	public DB(Dao<E> dao) {
		this.dao = (Dao<Dto>) dao;
		information = new DBInfo(this.dao);
		columnNames = information.getColumns();
		query = information.getQuery();
	}

	/**
	 * @param columns
	 *   select文でテーブルから持ってきたいカラム名を入力。 引数に何も指定しない場合は*の扱いになる。
	 *   例 select id,name from user のid,nameがcolumns
	 * @return 次にwhere句を指定する。
	 */
	public Where<E> select(String... columns) {
		CreateQueryMethod.appendQuery(query, "select");
		if (columns.length == 0) {
			CreateQueryMethod.appendQuery(query, "*", "from", dao.getTableName());
			return new Where<E>(information);
		}
		for (String string : columns) {
			columnNames.add(string);
		}
		CreateQueryMethod.setValuesWithComma(query, columns);
		CreateQueryMethod.appendQuery(query, "from", dao.getTableName());
		return new Where<E>(information);
	}

	/**
	 * インサートするカラムを指定。
	 * まだ、実際の値は入れない。
	 * id = 1でインサートしたい場合はidを入力
	 * @param columns
	 * @return
	 */
	public Values<E> insert(String... columns) {
		CreateQueryMethod.appendQuery(information.getQuery(), "insert", "into",
				dao.getTableName(),
				"(");
		if (columns.length == 0) {
			ArrayList<String> allColums = information.getDto().getFieldNames();
			if (information.getDto().isAutoIncrement()) {
				System.out.println("this dto is auto increment.");
				allColums.remove(0);
			}
			if (information.getDto().isLogicalDelete()) {
				System.out.println("this dto is logical delete.");
				allColums.remove(allColums.size() - 1);
			}
			columns = allColums.toArray(new String[allColums.size()]);
		}
		for (String string : columns) {
			columnNames.add(string);
		}
		//information.setColumns((ArrayList<String>) Arrays.asList(columns));
		CreateQueryMethod.setValuesWithComma(information.getQuery(), columns);
		CreateQueryMethod.appendQuery(information.getQuery(), ")");
		return new Values<E>(information);
	}

	public Set<E> update() {
		CreateQueryMethod.appendQuery(query, "update", dao.getTableName());
		return new Set<E>(information);
	}

	public Where<E> delete() {
		if (information.getDto().isLogicalDelete()) {
			update().set("deleted", true);
			return new Where<E>(information);
		}
		CreateQueryMethod.appendQuery(information.getQuery(), "delete", "from",
				dao.getTableName());
		return new Where<E>(information);
	}

	public ExecuteQuery<E> ExecuteRowQuery(String rowQuery, Object... preparedStatements) {
		query.append(rowQuery);
		ArrayList<Object> statements = information.getPreparedStatements();
		for (Object preparedStatement : preparedStatements) {
			statements.add(preparedStatement);
		}
		return new ExecuteQuery<>(information);
	}

	public ExecuteUpdate<E> ExecuteRowUpdate(String rowQuery, Object... preparedStatements) {
		query.append(rowQuery);
		ArrayList<Object> statements = information.getPreparedStatements();
		for (Object preparedStatement : preparedStatements) {
			statements.add(preparedStatement);
		}
		return new ExecuteUpdate<>(information);
	}

}
