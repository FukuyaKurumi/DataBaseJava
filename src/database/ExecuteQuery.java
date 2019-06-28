package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import env.Env;

public class ExecuteQuery {
	private Dto table;
	ArrayList<String> columns;

	ArrayList<Dto> executeQuery(DBInfo infomation) {
		this.table = table;
		this.columns = columns;
		System.out.println("executeQuery : " + infomation.getQuery());
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Dto> result = new ArrayList<>();

		try {
			// access the database.
			Class.forName(Env.DRIVER.toString());
			connection = DriverManager.getConnection(Env.DIR.toString(), Env.USER.toString(),
					Env.PASS.toString());

			// create sql and run it.
			preparedStatement = connection.prepareStatement(infomation.getQuery().toString());
			resultSet = preparedStatement.executeQuery();
			return result(resultSet, result);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	private ArrayList<Dto> result(ResultSet resultSet, ArrayList<Dto> result)
			throws ClassNotFoundException, SQLException {
		if (columns.size() == 0) {
			columns = new DtoMethod<>().getFieldNames(table);
		}
		while (resultSet.next()) {
			for (String s : columns) {
				table.setFields(s, resultSet.getString(s));
				System.out.println(table.getFields(s));
			}
			Dto tmpDto;
			try {
				tmpDto = Dto.deepcopy(table);
			} catch (IOException e) {
				System.out.println("deepcopy error.");
				e.printStackTrace();
				return null;
			}
			result.add(tmpDto);
		}
		return result;
	}

}
