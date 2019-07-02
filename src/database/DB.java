package database;

import java.util.ArrayList;

public class DB<E extends Dto> {
	DBInfo information;

	public DB(Dao<E> dao) {
		information = new DBInfo(dao, dao.getInstance().getDto());
	}

	/**
	 *
	 * @param columns
	 *            select文でテーブルから持ってきたいカラム名を入力。 引数に何も指定しない場合は*の扱いになる。 例 select
	 *            id,name from user のid,nameがcolumns
	 * @return 次にwhere句を指定する。
	 */
	public Where<E> select(String... columns) {
		System.out.print("set select phrase... : ");
		CreateQueryMethod.appendQuery(information.getQuery(), "select");
		if (columns.length == 0) {
			CreateQueryMethod.appendQuery(information.getQuery(), "*", "from",
					information.getDao().getTableName());
			System.out.println(information.getQuery().toString());
			return new Where<E>(information);
		}
		for (String string : columns) {
			information.getColumns().add(string);
		}
		//		information.setColumns((ArrayList<String>) Arrays.asList(columns));
		for (String value : columns) {
			information.getColumns().add(value);
		}
		CreateQueryMethod.setValuesWithComma(information.getQuery(), columns);
		CreateQueryMethod.appendQuery(information.getQuery(), "from",
				information.getDao().getTableName());
		System.out.println(information.getQuery());
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
		System.out.print("set insert phrase... : ");
		CreateQueryMethod.appendQuery(information.getQuery(), "insert", "into",
				information.getDao().getTableName(),
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
			information.getColumns().add(string);
		}
		//information.setColumns((ArrayList<String>) Arrays.asList(columns));
		CreateQueryMethod.setValuesWithComma(information.getQuery(), columns);
		CreateQueryMethod.appendQuery(information.getQuery(), ")");
		System.out.println(information.getQuery());
		return new Values<E>(information);
	}

	public Set<E> update() {
		System.out.print("set update phrase... : ");
		CreateQueryMethod.appendQuery(information.getQuery(), "update",
				information.getDao().getTableName());
		return new Set<E>(information);
	}

	public Where<E> delete() {
		System.out.print("set delete phrase... : ");
		CreateQueryMethod.appendQuery(information.getQuery(), "delete", "from",
				information.getDao().getTableName());
		return new Where<E>(information);
	}

}
