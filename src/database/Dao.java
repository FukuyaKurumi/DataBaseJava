package database;

import java.util.ArrayList;

public abstract class Dao<E extends Dto> {

	public Dao<E> getInstance() {
		return this;
	}

	public abstract String getTableName();

	public ArrayList<E> changeType(ArrayList<Dto> resultDtos) {
		ArrayList<E> result = new ArrayList<>();
		return result;
	}

	public abstract E getDto();
}
