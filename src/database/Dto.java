package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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


}
