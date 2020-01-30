package database;

import java.util.ArrayList;

public abstract class Dao<E extends Dto> {

	public abstract Dao<E> getInstance();

	public abstract String getTableName();

	public ArrayList<E> changeType(ArrayList<Dto> resultDtos) {
		ArrayList<E> result = new ArrayList<>();
		for (E e : result) {
			result.add((E) e);
		}
		return result;
	}

	public abstract E getDto();
}
