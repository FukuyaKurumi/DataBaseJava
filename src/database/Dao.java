package database;

import java.util.ArrayList;

public class Dao {
	private Dto table;
	private StringBuilder query = new StringBuilder();
	private String tableName;
	private ArrayList<String> colums = new ArrayList<>();

	public Dao(Dto table) {
		this.table = table;
		tableName = table.getTableName();
	}

	/**
	 *
	 * @param columns
	 *            select文でテーブルから持ってきたいカラム名を入力。 引数に何も指定しない場合は*の扱いになる。\n 例 select
	 *            id,name from user のid,nameがcolumns
	 * @return 次にwhere句を指定する。
	 */
	public Where select(String... columns) {
		System.out.print("set select phrase... : ");
		query.append(" select ");
		if (columns.length == 0) {
			query.append(" * from " + tableName);
			System.out.println(query.toString());
			return new Where(query, table, this.colums);
		}
		for (String column : columns) {
			query.append(" " + column + " , ");
			this.colums.add(column);
		}
		query.delete(query.length() - 2, query.length());
		query.append(" from " + tableName);
		System.out.println(query);
		return new Where(query, table, this.colums);
	}

}
