package com.erofeev.hotel.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;

public class Serializator {
	private static final Logger loggerSerializator = LogManager.getLogger(Serializator.class);

	public boolean serialize(ArrayList list, String fileName) {
		boolean flag = false;
		Path path = Paths.get(fileName);
		ObjectOutputStream objectStream = null;
		FileOutputStream fileStream = null;
		try {

			fileStream = new FileOutputStream((fileName));
			objectStream = new ObjectOutputStream(fileStream);
			objectStream.writeObject(list);
			flag = true;

		} catch (FileNotFoundException e) {

			loggerSerializator.warn("" + e.getStackTrace());
		} catch (IOException e) {
			loggerSerializator.warn("" + e.getStackTrace());
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				loggerSerializator.warn("cant close FileOutputStream" + e.getStackTrace());
			}
			try {
				objectStream.close();
			} catch (IOException e) {
				loggerSerializator.warn("cant close ObjectOutputStream" + e.getStackTrace());
			}

		}
		return flag;
	}

	public ArrayList<Service> deserialize(String fileName, Service entity) {
		ArrayList<Service> recordList = new ArrayList<>();
		try (FileInputStream fileStream = new FileInputStream(new File(fileName));
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

			recordList = (ArrayList<Service>) objectStream.readObject();
		} catch (FileNotFoundException e) {
			loggerSerializator.warn("cant close FileInputStream" + e.getStackTrace());
		} catch (IOException e) {
			loggerSerializator.warn("cant close ObjectInputStream" + e.getStackTrace());
		} catch (ClassNotFoundException e) {
			loggerSerializator.warn("Class of a serialized object cannot be found" + e.getStackTrace());
			e.printStackTrace();
		}
		return recordList;
	}

	public ArrayList<Room> deserialize(String fileName, Room entity) {
		ArrayList<Room> recordList = new ArrayList<>();
		try (FileInputStream fileStream = new FileInputStream(new File(fileName));
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

			recordList = (ArrayList<Room>) objectStream.readObject();

		} catch (FileNotFoundException e) {
			loggerSerializator.warn("cant close FileInputStream" + e.getStackTrace());
		} catch (IOException e) {
			loggerSerializator.warn("cant close ObjectInputStream" + e.getStackTrace());
		} catch (ClassNotFoundException e) {
			loggerSerializator.warn("Class of a serialized object cannot be found" + e.getStackTrace());
			e.printStackTrace();
		}
		return recordList;
	}

	public ArrayList<Guest> deserialize(String fileName, Guest entity) {
		ArrayList<Guest> recordList = new ArrayList<>();
		try (FileInputStream fileStream = new FileInputStream(new File(fileName));
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

			recordList = (ArrayList<Guest>) objectStream.readObject();

		} catch (FileNotFoundException e) {
			loggerSerializator.warn("cant close FileInputStream" + e.getStackTrace());
		} catch (IOException e) {
			loggerSerializator.warn("cant close ObjectInputStream" + e.getStackTrace());
		} catch (ClassNotFoundException e) {
			loggerSerializator.warn("Class of a serialized object cannot be found" + e.getStackTrace());
			e.printStackTrace();
		}
		return recordList;
	}

}
