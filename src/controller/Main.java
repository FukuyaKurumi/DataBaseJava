package controller;

import dao.SampleDao;
import database.DB;
import database.Operator;

public class Main {

	public static void main(String[] args) {
		new DB(SampleDao.getInstance(), SampleDao.getType()).select().where("id", Operator.EQUAL,
				2);
	}

}
