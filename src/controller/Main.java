package controller;

import java.util.ArrayList;

import database.Dao;
import database.Operator;
import dto.SampleDto;

public class Main {

	public static void main(String[] args) {
		ArrayList<SampleDto> sampleDtos = new Dao(new SampleDto()).select("id")
				.where("id", Operator.EQUAL, 2).executeQuery();
	}

}
