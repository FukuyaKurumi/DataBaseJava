package dao;

import java.util.ArrayList;

import database.Dao;
import database.Dto;
import dto.SampleDto;

public class SampleDao extends Dao<SampleDto> {
	private static SampleDto dto = new SampleDto();
	private static SampleDao singleton;
	private static String tableName = "sample";

	public SampleDao() {
		singleton = this;
	}

	@Override
	public SampleDao getInstance() {
		return singleton;
	}

	@Override
	public SampleDto getDto() {
		return dto;
	}

	@Override
	public ArrayList<SampleDto> changeType(ArrayList<Dto> resultDtos) {
		ArrayList<SampleDto> result = new ArrayList<>();
		for (Dto dto : resultDtos) {
			result.add((SampleDto) dto);
		}
		return result;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

}
