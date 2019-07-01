package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class Dto implements Serializable {

	@SuppressWarnings("unchecked")
	public static <T> T deepcopy(T obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectOutputStream(baos).writeObject(obj);
		return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
	}

	/**
	 * フィールド名からsetterを呼び出すメソッド
	 *
	 * @param fieldname
	 *            フィールド名
	 * @param field
	 *            セットしたいフィールドの値
	 */
	public abstract void setFields(String fieldname, String field);

	/**
	 * フィールドの日本語表示名を返すメソッド
	 *
	 * @return フィールドの日本語表示名
	 */
	public abstract ArrayList<String> JapanName();

	/**
	 * フィールド名からgetterを呼び出すメソッド
	 *
	 * @param fieldname
	 *            値を得たいフィールドの名前
	 * @return フィールドの値
	 */
	public abstract String getFields(String fieldname);

	/**
	 * 主キーがauto_incrementかどうか
	 *
	 * @return true:実装している
	 * @return false:実装していない
	 */
	public abstract Boolean isAutoIncrement();

	/**
	 * 論理削除を実装しているかどうか
	 *
	 * @return true:実装している
	 * @return false:実装していない
	 */
	public abstract Boolean isLogicalDelete();

	/**
	 * Dtoの主キーの数を返すメソッド
	 *
	 * @return 主キーの数
	 */
	public abstract int primaryKeys();

	/**
	 *  method to return a name list of all fields.
	 * @param dto
	 * @return
	 */
	public ArrayList<String> getFieldNames() {
		Dto dto = this;
		ArrayList<String> result = new ArrayList<>();
		for (Field f : dto.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				result.add(f.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} finally {
				f.setAccessible(false);
			}
		}
		return result;
	}

	/**
	 *  method to return a value list of all fields.
	 * @param dto
	 * @return
	 */
	public ArrayList<String> getFields() {
		Dto dto = this;
		ArrayList<String> fields = new ArrayList<>();
		for (Field f : dto.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.get(dto) == null) {
					fields.add("null");
				} else {
					fields.add(f.get(dto).toString());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} finally {
				f.setAccessible(false);
			}
		}
		return fields;
	}

	/**
	 *  method to return a class list of all fields.
	 * @param dto
	 * @return
	 */
	public ArrayList<Class<?>> getFieldClasses(Dto dto) {
		Dto dto2 = this;
		ArrayList<Class<?>> result = new ArrayList<>();
		for (Field f : dto.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			try {
				result.add(f.getType());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} finally {
				f.setAccessible(false);
			}
		}
		return result;
	}

	/**
	 * mysqlのbooleanをjavaのbooleanに対応させるメソッド
	 * @param bool
	 * @return
	 */
	public static boolean parseBoolean(String bool) {
		if (bool.equals("1") || Boolean.parseBoolean(bool)) {
			return true;
		} else {
			return false;
		}
	}
}
