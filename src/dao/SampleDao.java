package dao;

import database.Dao;
import dto.SampleDto;

public class SampleDao extends Dao<SampleDto> {
	public static final SampleDto dto = new SampleDto();

	@Override
	public SampleDto getDto() {
		return dto;
	}

	@Override
	public String getTableName() {
		return "sample";
	}

}
