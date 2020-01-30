package dto;

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

	public void setDeleted(String deleted) {
		this.deleted = parseBoolean(deleted);
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
	public void setFields(String fieldname, Object field) {
		switch (fieldname) {
		case "id":
			setId((Integer) field);
			break;
		case "name":
			setName((String) field);
			break;
		case "deleted":
			setDeleted((Boolean) field);
			break;
		default:
			System.out.println("this field does not exist!( ;∀;)\nfieldname:" + fieldname
					+ "\nfield:" + field);
		}
	}


	@Override
	public Boolean isAutoIncrement() {
		return false;
	}

	@Override
	public Boolean isLogicalDelete() {
		return true;
	}

}
