package database;

import java.util.ArrayList;

public final class DBInfo {
	private Dao<Dto> dao;
	private Dto dto;
	private StringBuilder query = new StringBuilder();
	private ArrayList<String> columns = new ArrayList<>();
	private ArrayList<Object> preparedStatements = new ArrayList<>();

	public ArrayList<Object> getPreparedStatements() {
		return preparedStatements;
	}

	/**
	 * プリペアードステートメントを追加するメソッド。
	 * 配列でもアレイリストでもエラーは出ないけど、プリペアードステートメントを
	 * 置き換えるときに一個づついれないとエラーになります。
	 * @param e
	 */
	public void addStatement(Object e) {
		if (e != null) {
			preparedStatements.add(e);
		}
	}

	public DBInfo(Dao<Dto> dao) {
		this.dao = dao;
		this.dto = dao.getDto();
	}

	public Dao<Dto> getDao() {
		return dao;
	}

	public void setDao(Dao<Dto> dao) {
		this.dao = dao;
	}

	public Dto getDto() {
		return dto;
	}

	public void setDto(Dto dto) {
		this.dto = dto;
	}

	public StringBuilder getQuery() {
		return query;
	}

	public void setQuery(StringBuilder query) {
		this.query = query;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	protected void setStringColumns(String... values) {
		for (String value : values) {
			setColumn("\"" + value + "\"");
		}
	}

	protected void setColumns(String... values) {
		for (String value : values) {
			setColumn(value);
		}
	}

	private void setColumn(String value) {
		query.append(" " + value + " ");
	}
}
