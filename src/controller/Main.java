package controller;

import java.util.ArrayList;

import dao.SampleDao;
import database.DB;
import database.Operator;
import dto.SampleDto;

public class Main {

	public static void main(String[] args) {
		ArrayList<SampleDto> samples = new DB<SampleDto>(new SampleDao()).select()
				.where("name", Operator.LIKE, "%a%")
				.executeQuery();

		for (SampleDto sampleDto : samples) {
			ArrayList<String> fields = sampleDto.getFieldNames();
			for (String string : fields) {
				System.out.print(string + " : ");
				System.out.println(sampleDto.getFields(string));
			}
		}

	}

}
