package database;

import java.sql.DriverManager;
import java.sql.SQLException;

import env.Env;

public class ExecuteUpdate<E extends Dto> extends Execute {

	public ExecuteUpdate(DBInfo information) {
		this.information = information;
	}

	public int executeUpdate() {
		System.out.println("executeUpdate : " + information.getQuery());
		int result = 0;
		try {
			// access the database.
			Class.forName(Env.DRIVER.toString());
			connection = DriverManager.getConnection(Env.DIR.toString(), Env.UPDATE_USER.toString(),
					Env.UPDATE_PASS.toString());

			// create sql and run it.
			preparedStatement = connection.prepareStatement(information.getQuery().toString());
			setPreparedStatement();
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
