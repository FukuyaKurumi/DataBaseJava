package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import env.Env;

public class ExecuteQuery<E extends Dto> {
	DBInfo information;

	ArrayList<E> executeQuery(DBInfo infomation) {
		this.information = infomation;
		System.out.println("executeQuery : " + infomation.getQuery());
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<E> result = new ArrayList<>();

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

	private ArrayList<E> result(ResultSet resultSet, ArrayList<E> result)
			throws ClassNotFoundException, SQLException {
		ArrayList<String> columns = information.getColumns();
		Dto dto = information.getDto();
		if (columns.size() == 0) {
			information.setColumns(new DtoMethod<>()
					.getFieldNames(information.getDao().getTableName()));
		}
		while (resultSet.next()) {
			for (String s : columns) {
				dto.setFields(s, resultSet.getString(s));
				System.out.println(dto.getFields(s));
			}
			Dto tmpDto;
			try {
				tmpDto = Dto.deepcopy(dto);
			} catch (IOException e) {
				System.out.println("deepcopy error.");
				e.printStackTrace();
				return null;
			}
			result.add((E) tmpDto);
		}
		return result;
	}

}
