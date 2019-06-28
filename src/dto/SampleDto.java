package dto;

import java.util.ArrayList;
import java.util.Arrays;

import database.Dto;

public class SampleDto extends Dto {
	private int id;
	private String name;
	private boolean deleted;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setFields(String fieldname, String field) {
		switch (fieldname) {
		case "id":
			setId(Integer.parseInt(field));
			break;
		case "name":
			setName(field);
			break;
		case "deleted":
			setDeleted(Boolean.parseBoolean(field));
		default:
			System.out.println("this field does not exist!( ;∀;)\nfieldname:" + fieldname + "\nfield:" + field);
		}
	}

	@Override
	public ArrayList<String> JapanName() {
		String[] tmp = { "ID", "名前" };
		ArrayList<String> result = new ArrayList<>(Arrays.asList(tmp));
		return result;
	}

	@Override
	public String getFields(String fieldname) {
		switch (fieldname) {
		case "id":
			return Integer.toString(getId());
		case "name":
			return getName();
		case "deleted":
			return Boolean.toString(isDeleted());
		default:
			System.out.println("this field does not exist!( ;∀;)\nfieldname:" + fieldname);
		}
		return null;
	}

	@Override
	public Boolean isAutoIncrement() {
		return false;
	}

	@Override
	public Boolean isLogicalDelete() {
		return true;
	}

	@Override
	public int primaryKeys() {
		return 1;
	}

	@Override
	public String getTableName() {
		return "sample";
	}

}
