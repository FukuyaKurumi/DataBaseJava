package database;

import java.sql.SQLException;

public class ExecuteUpdate<E extends Dto> extends DBConnection {
	private DBInfo information;

	public ExecuteUpdate(DBInfo information) {
		this.information = information;
	}

	public int executeUpdate() {
		System.out.println("executeUpdate : " + information.getQuery());
		int result = 0;
		try {
			createConnection(information);
			result = preparedStatement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

}
