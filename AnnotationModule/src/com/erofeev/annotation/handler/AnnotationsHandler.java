package com.erofeev.annotation.handler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.annotation.CsvEntity;
import com.erofeev.annotation.CsvProperty;
import com.erofeev.annotation.PropertyType;
import com.erofeev.annotation.csvwriter.CsvWriter;
import com.erofeev.annotation.dataobjects.EntityData;
import com.erofeev.annotation.dataobjects.FieldData;
import com.erofeev.hotel.properties.Config;

public class AnnotationsHandler {
	private static final Logger loggerAnnotationsHandler = LogManager.getLogger(AnnotationsHandler.class);
	private static Set<EntityData> fullList = new LinkedHashSet<EntityData>();
	private static List<EntityData> rooms = new ArrayList<EntityData>();
	private static List<EntityData> services = new ArrayList<EntityData>();
	private static List<EntityData> guests = new ArrayList<EntityData>();

	public static Set<EntityData> getFullList() {
		return fullList;
	}

	public static void setFullList(Set<EntityData> fullList) {
		AnnotationsHandler.fullList = fullList;
	}

	public static List<EntityData> getRooms() {
		return rooms;
	}

	public static void setRooms(List<EntityData> rooms) {
		AnnotationsHandler.rooms = rooms;
	}

	public static List<EntityData> getServices() {
		return services;
	}

	public static void setServices(List<EntityData> services) {
		AnnotationsHandler.services = services;
	}

	public static List<EntityData> getGuests() {
		return guests;
	}

	public static void setGuests(List<EntityData> guests) {
		AnnotationsHandler.guests = guests;
	}

	private static boolean isInCollection(List<EntityData> list, EntityData entity) {
		boolean flag = false;
		for (EntityData entityData : list) {
			if (entityData.equals(entity)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	private static void sortByEntity() {
		for (EntityData entityData : fullList) {
			if (entityData.getFileName().equals("guests.csv")) {
				if (!isInCollection(guests, entityData)) {
					guests.add(entityData);
				}

			}

			if (entityData.getFileName().equals("rooms.csv")) {
				if (!isInCollection(rooms, entityData)) {
					rooms.add(entityData);
				}
			}

			if (entityData.getFileName().equals("services.csv")) {
				if (!isInCollection(services, entityData)) {
					services.add(entityData);
				}
			}

		}
	}

	private static boolean isCollection(Object obj) {
		boolean flag = false;
		Class<? extends Object> mClassObject = obj.getClass();
		Class[] interfaces = mClassObject.getInterfaces();

		for (Class<?> class1 : interfaces) {
			if (class1.getName().equals("java.util.List")) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static boolean isCSVEntity(Object obj) {
		Class<? extends Object> mClassObject = obj.getClass();
		CsvEntity annotation = mClassObject.getAnnotation(CsvEntity.class);
		Class<CsvEntity> annotationType = CsvEntity.class;
		if (annotation != null) {
			if (annotation.annotationType().equals(annotationType)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isCSVProperty(Field field) {
		Class<CsvProperty> annotationType = CsvProperty.class;
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		if (annotation != null) {
			if (annotation.annotationType().equals(annotationType)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isCSVPropertyEscape(Field field) {
		boolean escape = false;
		Class<CsvProperty> annotationType = CsvProperty.class;
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		if (annotation != null) {
			if (annotation.annotationType().equals(annotationType)) {
				escape = annotation.escape();
			}
		}

		return escape;
	}

	private static boolean isCSVPropertySimple(Field field) {
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		PropertyType type = annotation.propertyType();
		if (type == (PropertyType.SimpleProperty)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isCSVPropertyComplete(Field field) {
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		PropertyType type = annotation.propertyType();
		if (type == (PropertyType.CompositeProperty)) {
			return true;
		} else {
			return false;
		}
	}

	private static int getIdEntity(Object obj) {
		Class<? extends Object> mClassObject = obj.getClass();
		int id;
		Method m = null;
		try {
			m = mClassObject.getDeclaredMethod("getId");
			id = (int) m.invoke(obj);
			return id;
		} catch (NoSuchMethodException e) {
			loggerAnnotationsHandler.warn("Methode doesn't exist " + m.getName());
		} catch (SecurityException e) {
			loggerAnnotationsHandler.warn("Security violation " + m.getName());
		} catch (IllegalAccessException e) {
			loggerAnnotationsHandler.warn("Can't get access to methode ");
		} catch (IllegalArgumentException e) {
			loggerAnnotationsHandler.warn("Wrong args in methode " + m.getName());
		} catch (InvocationTargetException e) {
			loggerAnnotationsHandler.warn(e.getCause().getMessage());
		}

		return -1;
	}

	private static List<FieldData> getAnnotatedFields(Object obj)
			throws IllegalArgumentException, IllegalAccessException {
		Class<? extends Object> mClassObject = obj.getClass();
		List<FieldData> fullFieldList = new ArrayList<FieldData>();
		Field[] fields = mClassObject.getDeclaredFields();
		for (Field field : fields) {
			if (isCSVProperty(field)) {
				FieldData fieldData = getFieldValue(field, obj);
				if (!(fieldData == null)) {
					fullFieldList.add(fieldData);
				}
			}
		}
		return fullFieldList;
	}

	private static FieldData getFieldSimple(Field field, Object obj) {
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		FieldData fieldData = new FieldData();
		field.setAccessible(true);
		fieldData.setColumnNumber(String.valueOf(annotation.columnNumber()));
		String value;
		try {
			value = field.get(obj).toString();
			fieldData.setFieldValue(value);
			field.setAccessible(false);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			loggerAnnotationsHandler.warn("Wrong args in the methode ");
		}

		return fieldData;
	}

	private static FieldData getFieldComposite(Field field, Object obj) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String separator = "|";
		FieldData fieldData = new FieldData();
		CsvProperty annotation = field.getAnnotation(CsvProperty.class);
		field.setAccessible(true);
		fieldData.setColumnNumber(String.valueOf(annotation.columnNumber()));
		Object obj2 = field.get(obj);
		if (isCollection(obj2)) {
			List list = (List) obj2;
			StringBuilder idEnteringToCollection = new StringBuilder();
			for (Object object : list) {
				if (isCSVEntity(object)) {
					Class<? extends Object> mClassObject = object.getClass();
					Method m = mClassObject.getDeclaredMethod("getId");
					idEnteringToCollection.append((int) m.invoke(object)).append(separator);
					getEntityData(object);
				}
			}
			fieldData.setKeyField(idEnteringToCollection.toString());
		} else {
			Class<? extends Object> mClassObject = obj2.getClass();
			Method m = mClassObject.getDeclaredMethod("getId");
			fieldData.setKeyField(String.valueOf(m.invoke(obj2)));
			field.setAccessible(false);
			getEntityData(obj2);
		}
		return fieldData;
	}

	private static FieldData getFieldValue(Field field, Object obj) {
		FieldData fieldData = new FieldData();
		if (isCSVProperty(field)) {
			if (isCSVPropertyEscape(field)) {
				fieldData.setFieldValue("null");
				return fieldData;

			} else {
				if (isCSVPropertyComplete(field)) {
					try {
						fieldData = getFieldComposite(field, obj);
						return fieldData;
					} catch (InvocationTargetException | NoSuchMethodException | SecurityException e) {
						loggerAnnotationsHandler.warn("Methode doesn't exist");
					} catch (IllegalAccessException e) {
						loggerAnnotationsHandler.warn("Can't get access");
					} catch (IllegalArgumentException e) {
						loggerAnnotationsHandler.warn("Wrong args in the methode");
					}
				}
				if (isCSVPropertySimple(field)) {
					try {
						fieldData = getFieldSimple(field, obj);
					} catch (IllegalArgumentException e) {
						loggerAnnotationsHandler.warn("Wrong args in the methode");
					}
					return fieldData;
				}
			}

		} else {

		}
		return null;

	}

	/*
	 * private static boolean isEntityReadBefore(List<EntityData> all,
	 * EntityData entity) { for (EntityData entityData : all) { if
	 * (entityData.getFileName().equals(entity.getFileName())) { if
	 * (entityData.getId() == entity.getId()) { return true; } }
	 * 
	 * }
	 * 
	 * return false; }
	 */

	public static EntityData getEntityData(Object obj) {
		EntityData entityData = new EntityData();
		Set<EntityData> all = new HashSet<EntityData>();
		Class<? extends Object> mClassObject = obj.getClass();
		CsvEntity annotation = mClassObject.getAnnotation(CsvEntity.class);

		if (isCollection(obj)) {
			List list = (List) obj;
			for (Object object : list) {
				if (isCSVEntity(object)) {
					getEntityData(object);
				}
			}
		}

		if (isCSVEntity(obj)) {
			entityData.setFileName(annotation.filename());
			entityData.setSeparator(annotation.valuesSeparator());
			try {
				entityData.setId(getIdEntity(obj));
				List<FieldData> fieldData = getAnnotatedFields(obj);
				entityData.setFields(fieldData);
				entityData.setStatusRead(true);
				all.add(entityData);
				fullList.add(entityData);

			} catch (IllegalAccessException | IllegalArgumentException e) {
				loggerAnnotationsHandler.warn("Illegal argument or can't get access to class methode");
				e.printStackTrace();
			} catch (NullPointerException e) {
				loggerAnnotationsHandler.warn("Empty fields");
			}
		}
		return entityData;
	}

	public static Set<EntityData> getAllEntitiesFromObject(Object obj) {
		fullList.clear();
		getEntityData(obj);
		for (EntityData entityData : fullList) {
			System.out.println(entityData);
		}
		sortByEntity();
		return fullList;
	}

}
