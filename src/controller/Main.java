package controller;

import java.util.ArrayList;

import dao.SampleDao;
import database.DB;
import database.Operator;
import dto.SampleDto;

public class Main {

	public static void main(String[] args) {
		ArrayList<SampleDto> samples = new DB<SampleDto>(new SampleDao()).select("id", "name")
				.where("name", Operator.EQUAL, "banana").executeQuery();
		for (SampleDto sampleDto : samples) {
			ArrayList<String> fields = sampleDto.getFieldNames();
			for (String string : fields) {
				System.out.print(string + " : ");
				System.out.println(sampleDto.getFields(string));
			}
		}
		samples = new DB<SampleDto>(new SampleDao()).select()
				.where("name", Operator.LIKE, "%o%").executeQuery();
		for (SampleDto sampleDto : samples) {
			ArrayList<String> fields = sampleDto.getFieldNames();
			for (String string : fields) {
				System.out.print(string + " : ");
				System.out.println(sampleDto.getFields(string));
			}
		}

//		int num = new DB<SampleDto>(new SampleDao()).insert("name").values("watermeron")
//				.executeUpdate();
//
//		System.out.println("insert : " + num);
	}

}
