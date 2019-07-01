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

	/**
	 * リザルトセットを受け取ってDtoアレイリストに変換するメソッド
	 * @param resultSet
	 * @param result
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private ArrayList<E> result(ResultSet resultSet, ArrayList<E> result)
			throws ClassNotFoundException, SQLException {
		System.out.println("start ExecuteQuery.result");
		ArrayList<String> columns = information.getColumns();
		Dto dto = information.getDto();
		if (columns.size() == 0) {
			System.out.println(information.getDto().getFieldNames());
			information.setColumns(information.getDto().getFieldNames());
			columns = information.getColumns();
		}
		while (resultSet.next()) {
			System.out.println("check resultSet");
			for (String s : columns) {
				System.out.println("kokodayo! : " + s);
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
