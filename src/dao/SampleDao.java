package dao;

import database.Dao;
import dto.SampleDto;

public class SampleDao extends Dao<SampleDto> {
	public static final SampleDao singleton = new SampleDao();
	public static final SampleDto dto = new SampleDto();

	@Override
	public SampleDao getInstance() {
		return singleton;
	}

	@Override
	public SampleDto getDto() {
		return dto;
	}

	@Override
	public String getTableName() {
		return "sample";
	}

}
