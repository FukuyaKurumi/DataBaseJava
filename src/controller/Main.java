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
				.where("name", Operator.LIKE, "%b%").executeQuery();
		for (SampleDto sampleDto : samples) {
			ArrayList<String> fields = sampleDto.getFieldNames();
			for (String string : fields) {
				System.out.print(string + " : ");
				System.out.println(sampleDto.getFields(string));
			}
		}

		int num = new DB<SampleDto>(new SampleDao()).insert("id", "name", "deleted")
				.values(10, "watermeron", false).executeUpdate();
		System.out.println("insert : " + num);

		int num2 = new DB<SampleDto>(new SampleDao()).update().set("name", "suika")
				.where("name", Operator.EQUAL, "watermeron").executeUpdate();
		System.out.println(num2);

		int num3 = new DB<SampleDto>(new SampleDao()).delete().where("id", Operator.EQUAL, 10)
				.executeUpdate();
		System.out.println(num3);
	}

}
