package database;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;

import env.Env;

public class ExecuteQuery<E extends Dto> extends Execute {

	public ExecuteQuery(DBInfo information) {
		super();

		super.information = information;
	}

	public ArrayList<E> executeQuery() {
		System.out.println("executeQuery : " + information.getQuery());
		ArrayList<E> result = new ArrayList<>();

		try {
			Class.forName(Env.DRIVER.toString());
			connection = DriverManager.getConnection(Env.DIR.toString(), Env.UPDATE_USER.toString(),
					Env.UPDATE_PASS.toString());

			// set prepared statement
			preparedStatement = connection.prepareStatement(information.getQuery().toString());
			setPreparedStatement();
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
	@SuppressWarnings("unchecked")
	private ArrayList<E> result(ResultSet resultSet, ArrayList<E> result)
			throws ClassNotFoundException, SQLException {
		ArrayList<String> columns = information.getColumns();
		Dto dto = information.getDto();
		if (columns.size() == 0) {
			information.setColumns(dto.getFieldNames());
			columns = information.getColumns();
		}
		while (resultSet.next()) {
			for (String s : columns) {
				String className = dto.getFieldClass(s).getName();
				switch (className) {
				case "java.lang.String":
					dto.setFields(s, resultSet.getString(s));
					break;
				case "java.lang.Integer":
				case "int":
					dto.setFields(s, resultSet.getInt(s));
					break;
				case "java.lang.Double":
				case "double":
					dto.setFields(s, resultSet.getDouble(s));
					break;
				case "java.lang.Boolean":
				case "boolean":
					dto.setFields(s, resultSet.getBoolean(s));
					break;
				case "java.sql.Date":
					dto.setFields(s, resultSet.getDate(s));
					break;
				case "java.sql.Time":
					dto.setFields(s, resultSet.getTime(s));
					break;
				case "java.sql.Timestamp":
					dto.setFields(s, resultSet.getTimestamp(s));
					break;
				case "java.time.Year":
					Date y = resultSet.getDate(s);
					if (y == null) {
						break;
					}
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(y);
					dto.setFields(s, Year.of(calendar.get(Calendar.YEAR)));
					break;
				default:
					System.out.println("unecpected class : " + className.toString());
					break;
				}
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
