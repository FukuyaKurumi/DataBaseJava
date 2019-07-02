package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import env.Env;

public class DBConnection {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	void createConnection(DBInfo information) throws ClassNotFoundException, SQLException {
		// access the database.
		Class.forName(Env.DRIVER.toString());
		connection = DriverManager.getConnection(Env.DIR.toString(), Env.USER.toString(),
				Env.PASS.toString());

		// create sql and run it.
		preparedStatement = connection.prepareStatement(information.getQuery().toString());
	}
}
