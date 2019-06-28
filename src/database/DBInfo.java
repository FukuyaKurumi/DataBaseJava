package database;

import java.util.ArrayList;

public final class DBInfo {
	private Dao dao;
	private Dto dto;
	private StringBuilder query = new StringBuilder();
	private ArrayList<String> columns = new ArrayList<>();

	public DBInfo(Dao dao, Dto dto) {
		this.dao = dao;
		this.dto = dto;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
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
