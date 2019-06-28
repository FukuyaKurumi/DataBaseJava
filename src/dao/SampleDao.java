package dao;

import java.util.ArrayList;

import database.Dao;
import database.Dto;
import dto.SampleDto;

public class SampleDao extends Dao {
	private static SampleDto type;
	private static SampleDao singleton;
	private static String tableName;

	private SampleDao() {
		setSingleton();
		setTableName();
		setType();
	}

	public static SampleDao getInstance() {
		return singleton;
	}

	public static SampleDto getType() {
		return type;
	}

	public static ArrayList<SampleDto> changeType(ArrayList<Dto> resultDtos) {
		ArrayList<SampleDto> result = new ArrayList<>();
		for (Dto dto : resultDtos) {
			result.add((SampleDto) dto);
		}
		return result;
	}

	@Override
	protected void setTableName() {
		tableName = "sample";
	}

	@Override
	protected void setType() {
		type = new SampleDto();
	}

	@Override
	protected void setSingleton() {
		singleton = new SampleDao();
	}

	@Override
	protected String getTableName() {
		return tableName;
	}

}
