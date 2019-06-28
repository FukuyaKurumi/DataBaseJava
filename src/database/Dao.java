package database;

public abstract class Dao {

	protected abstract void setTableName();

	protected abstract String getTableName();

	protected abstract void setType();

	protected abstract void setSingleton();

	public static Dao getInstance() {
		return null;
	}

}
