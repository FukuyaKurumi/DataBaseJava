package database;

import java.util.ArrayList;
import java.util.Arrays;

public class DB<E extends Dto> implements CreateQueryMethod {
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
		appendQuery(information.getQuery(), "select");
		if (columns.length == 0) {
			appendQuery(information.getQuery(), "*", "from", information.getDao().getTableName());
			System.out.println(information.getQuery().toString());
			return new Where<E>(information);
		}
		information.setColumns((ArrayList<String>) Arrays.asList(columns));
		for (String value : columns) {
			information.getColumns().add(value);
		}
		setValuesWithComma(information.getQuery(), columns);
		appendQuery(information.getQuery(), "from", information.getDao().getTableName());
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
		appendQuery(information.getQuery(), "insert", "into", information.getDao().getTableName(),
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
		information.setColumns((ArrayList<String>) Arrays.asList(columns));
		setValuesWithComma(information.getQuery(), columns);
		appendQuery(information.getQuery(), ")", information.getDao().getTableName());
		System.out.println(information.getQuery());
		return new Values<E>(information);
	}

}
