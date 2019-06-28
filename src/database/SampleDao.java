package database;

import java.util.ArrayList;

import dto.Dto;
import dto.SampleDto;

public class SampleDao {
	private static SampleDto type = new SampleDto();
	private static SampleDao singleton = new SampleDao();

	protected SampleDao() {
	}

	public static SampleDao getInstance() {
		return singleton;
	}

	public static SampleDto getType() {
		return type;
	}

	private static ArrayList<SampleDto> changeType(ArrayList<Dto> resultDtos) {
		ArrayList<SampleDto> result = new ArrayList<>();
		for (Dto dto : resultDtos) {
			result.add((SampleDto) dto);
		}
		return result;
	}

}
