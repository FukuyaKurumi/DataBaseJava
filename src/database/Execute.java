package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

class Execute {
	protected DBInfo information;
	protected Connection connection = null;
	protected PreparedStatement preparedStatement = null;
	protected ResultSet resultSet = null;

	protected void setPreparedStatement() throws SQLException {
		ArrayList<Object> objects = information.getPreparedStatements();
		for (int i = 0; i < objects.size(); i++) {
			Object object = objects.get(i);
			String className = object.getClass().getName();
			switch (className) {
			case "java.lang.String":
				preparedStatement.setString(i + 1, (String) object);
				break;
			case "java.lang.Integer":
			case "int":
				preparedStatement.setInt(i + 1, (Integer) object);
				break;
			case "java.lang.Double":
			case "double":
				preparedStatement.setInt(i + 1, (Integer) object);
				break;
			case "java.lang.Boolean":
			case "boolean":
				preparedStatement.setBoolean(i + 1, (Boolean) object);
				break;
			case "java.sql.Date":
				preparedStatement.setDate(i + 1, (java.sql.Date) object);
				break;
			case "java.sql.Time":
				preparedStatement.setTime(i + 1, (Time) object);
				break;
			case "java.sql.Timestamp":
				preparedStatement.setTimestamp(i + 1, (Timestamp) object);
				break;
			default:
				System.out.println("unecpected class : " + className.toString());
				preparedStatement.setString(i + 1, "unkown");
				break;
			}
		}
	}
}
