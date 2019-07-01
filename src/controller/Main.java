package controller;

import java.util.ArrayList;

import dao.SampleDao;
import database.DB;
import database.DtoMethod;
import database.Operator;
import dto.SampleDto;

public class Main {

	public static void main(String[] args) {
		ArrayList<SampleDto> samples = new DB<SampleDto>(new SampleDao()).select()
				.where("id", Operator.EQUAL, 1).executeQuery();

		for (SampleDto sampleDto : samples) {
			ArrayList<String> fields = new DtoMethod().getFieldNames(sampleDto);
			for (String string : fields) {
				System.out.print(string + " : ");
				System.out.println(sampleDto.getFields(string));
			}
		}

	}

}
