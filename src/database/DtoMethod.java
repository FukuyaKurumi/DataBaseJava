package database;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DtoMethod<T> {

	/**
	 *  method to return a name list of all fields.
	 * @param dto
	 * @return
	 */
	public ArrayList<String> getFieldNames(T dto) {
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
	public ArrayList<String> getFields(T dto) {
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
	public ArrayList<Class<?>> getFieldClasses(T dto) {
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

}
