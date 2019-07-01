package database;

public class DB<E extends Dto> implements CreateQueryMethod {
	DBInfo information;

	public DB(Dao<E> dao) {
		information = new DBInfo(dao, dao.getInstance().getDto());
	}

	/**
	 *
	 * @param columns
	 *            select文でテーブルから持ってきたいカラム名を入力。 引数に何も指定しない場合は*の扱いになる。\n 例 select
	 *            id,name from user のid,nameがcolumns
	 * @return 次にwhere句を指定する。
	 */
	public Where<E> select(String... columns) {
		System.out.print("set select phrase... : ");
		setColumns(information.getQuery(), "select");
		if (columns.length == 0) {
			setColumns(information.getQuery(), "*", "from", information.getDao().getTableName());
			System.out.println(information.getQuery().toString());
			return new Where<E>(information);
		}
		for (String column : columns) {
			setColumns(information.getQuery(), column, ",");
			information.getColumns().add(column);
		}
		information.getQuery().delete(information.getQuery().length() - 2,
				information.getQuery().length());
		setColumns(information.getQuery(), "from", information.getDao().getTableName());
		System.out.println(information.getQuery());
		return new Where<E>(information);
	}

}
