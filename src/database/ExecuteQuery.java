package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExecuteQuery<E extends Dto> extends DBConnection {
	private DBInfo information;

	public ExecuteQuery(DBInfo information) {
		super();
		this.information = information;
	}

	ArrayList<E> executeQuery() {
		System.out.println("executeQuery : " + information.getQuery());
		ArrayList<E> result = new ArrayList<>();

		try {
			createConnection(information);
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
