package com.erofeev.hotel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.AbstractManager;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;

public class FileManager {

	private static final Logger loggerFileManager = LogManager.getLogger(FileManager.class);
	private static volatile FileManager instance;
	private Serializator serial = new Serializator();
	private String roomsPath;
	private String guestsPath;
	private String servicesPath;

	public void initFileManager(String ROOMS_FILE, String GUESTS_FILE, String SERVICES_FILE) {
		this.setRoomsPath(ROOMS_FILE);
		this.setGuestsPath(GUESTS_FILE);
		this.setServicesPath(SERVICES_FILE);

	}

	public static FileManager getInstance() {
		FileManager localInstance = instance;
		if (localInstance == null) {
			synchronized (FileManager.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new FileManager();
					loggerFileManager.debug("FileManager was created");
				}
			}
		}
		return localInstance;
	}

	public ArrayList<Room> readRoomsFromFile() {
		return serial.deserialize(this.getRoomsPath(), new Room());
	}

	public ArrayList<Guest> readGuestsFromFile() {
		return serial.deserialize(this.getGuestsPath(), new Guest());
	}

	public ArrayList<Service> readServicesFromFile() {
		return serial.deserialize(this.getServicesPath(), new Service());
	}

	public void saveToFile(AbstractManager manager) {

		String FILE_PATH = "";

		if (manager instanceof GuestManager) {
			FILE_PATH = this.getGuestsPath();
		}

		if (manager instanceof RoomManager) {
			FILE_PATH = this.getRoomsPath();
		}

		if (manager instanceof ServicesManager) {
			FILE_PATH = this.getServicesPath();
		}

		Path filePath = Paths.get(FILE_PATH);
		try {
			if (!Files.exists(filePath)) {
				Files.createFile(filePath);
			}

		} catch (UnsupportedOperationException | IOException | SecurityException e) {
			loggerFileManager.error("Can't create file " + e);
		}

		try {

			serial.serialize(manager.getAll(), FILE_PATH);

			loggerFileManager.debug(manager.getClass().getSimpleName() + "File was saved");
		} catch (RuntimeException e) {
			loggerFileManager.error(" Can't write to file " + e);
		} finally {

		}
	}

	public String getRoomsPath() {
		return roomsPath;
	}

	public void setRoomsPath(String roomsPath) {
		this.roomsPath = roomsPath;
	}

	public String getGuestsPath() {
		return guestsPath;
	}

	public void setGuestsPath(String guestsPath) {
		this.guestsPath = guestsPath;
	}

	public String getServicesPath() {
		return servicesPath;
	}

	public void setServicesPath(String servicesPath) {
		this.servicesPath = servicesPath;
	}

}
