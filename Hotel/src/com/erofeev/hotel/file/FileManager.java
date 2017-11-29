package com.erofeev.hotel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.TextFileWorker;
import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.AbstractManager;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.reception.Reception;

public class FileManager {
	private static final Logger loggerFileManager = LogManager.getLogger(FileManager.class);

	private static volatile FileManager instance;
	private String roomsPath;
	private String guestsPath;
	private String servicesPath;

	private FileManager() {

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

	public void initFileManager(String ROOMS_FILE, String GUESTS_FILE, String SERVICES_FILE) {
		this.setRoomsPath(ROOMS_FILE);
		this.setGuestsPath(GUESTS_FILE);
		this.setServicesPath(SERVICES_FILE);
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

		String[] strManager = manager.read();
		Path filePath = Paths.get(FILE_PATH);
		try {
			if (!Files.exists(filePath)) {
				Files.createFile(filePath);
			}

		} catch (UnsupportedOperationException | IOException | SecurityException e) {
			loggerFileManager.error("Can't create file " + e);

		}

		try {
			TextFileWorker fileManager = new TextFileWorker(FILE_PATH);

			fileManager.writeToFile(strManager);
			loggerFileManager.debug(manager.getClass().getSimpleName() + "File was saved");
		} catch (RuntimeException e) {
			loggerFileManager.error(" Can't write to file " + e);
		} finally {

		}

	}

	public ArrayList<Room> readRoomsFromFile() throws NumberFormatException, IOException {
		String ROOMS_FILE = this.getRoomsPath();
		ArrayList<Room> rooms = new ArrayList<Room>();
		Path filePath = Paths.get(ROOMS_FILE);
		if (!Files.exists(filePath)) {
			Files.createFile(filePath);
		}
		TextFileWorker fileWorker = new TextFileWorker(ROOMS_FILE);

		String[] readedValues = fileWorker.readFromFile();

		String roomName = "";
		int roomStars = 0;
		float roomPrice = 0;
		int roomCapacity = 0;
		boolean empty = true;
		RoomStatus roomStatus = RoomStatus.REPAIRABLE;
		Room room = null;

		for (int i = 0; i < readedValues.length; i++) {
			if ((readedValues[i] != null)) {
				String[] strValue = readedValues[i].split(" ");

				roomName = (strValue[0]);
				roomStars = Integer.parseInt(strValue[1]);
				roomPrice = Float.parseFloat(strValue[2]);
				roomCapacity = Integer.parseInt(strValue[3]);
				if (strValue[4].equals("true")) {
					empty = true;
				}
				if (strValue[4].equals("false")) {
					empty = false;
				} else {

				}
				switch (strValue[5]) {
				case "REPAIRABLE":
					roomStatus = RoomStatus.REPAIRABLE;
					break;
				case "SERVICED":
					roomStatus = RoomStatus.SERVICED;
					break;

				default:
					loggerFileManager.warn("Unidentified room status");
					break;
				}

				room = new Room(roomName, roomStars, roomPrice, roomCapacity);
				room.setEmpty(empty);
				room.setRoomStatus(roomStatus);
				rooms.add(room);
			}
		}

		return rooms;

	}

	public ArrayList<Service> readServicesFromFile() throws NumberFormatException, IOException {
		String SERVICES_FILE = this.getServicesPath();
		ArrayList<Service> services = new ArrayList<Service>();
		Path filePath = Paths.get(SERVICES_FILE);
		if (!Files.exists(filePath)) {
			Files.createFile(filePath);
		}
		TextFileWorker fileWorker = new TextFileWorker(SERVICES_FILE);
		String[] readedValues = fileWorker.readFromFile();
		String serviceName = "";
		float servicePrice = 0f;

		for (int i = 0; i < readedValues.length; i++) {
			String[] strValue = readedValues[i].split(" ");
			serviceName = strValue[0];
			servicePrice = Float.parseFloat(strValue[1]);
			Service service = new Service(serviceName, servicePrice);
			services.add(service);
		}
		return services;
	}

	public ArrayList<Guest> readGuestsFromFile(Reception reception)
			throws ParseException, NumberFormatException, NullPointerException, IOException {
		String GUESTS_FILE = this.getGuestsPath();
		ArrayList<Guest> guests = new ArrayList<Guest>();
		Path filePath = Paths.get(GUESTS_FILE);
		if (!Files.exists(filePath)) {

			Files.createFile(filePath);
		}

		TextFileWorker fileWorker = new TextFileWorker(GUESTS_FILE);
		String[] readedValues = fileWorker.readFromFile();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		String roomName = "";
		String guestFirstName = "";
		String guestSecondName = "";
		String serviceName = "";
		Date arrivalDate = null;
		Date leavingDate = null;
		Room room = null;
		Guest guest = null;
		Service service = null;

		for (int i = 0; i < readedValues.length; i++) {

			String[] strValue = readedValues[i].split(" ");
			guestFirstName = strValue[0];
			guestSecondName = strValue[1];
			arrivalDate = dateFormat.parse(strValue[2]);
			leavingDate = dateFormat.parse(strValue[3]);
			guest = new Guest(guestFirstName, guestSecondName, arrivalDate, leavingDate);
			roomName = strValue[4];
			ArrayList<Room> rooms = reception.getRoomManager().getAll();
			for (int k = 0; k < rooms.size(); k++) {
				if (rooms.get(k).getName().equals(roomName)) {
					room = rooms.get(k);
				}
			}
			room.setEmpty(false);
			guest.setRoom(room);
			for (int j = 5; j < strValue.length; j++) {
				serviceName = strValue[j];
				ArrayList<Service> services = reception.getServicesManager().getAll();
				for (int k = 0; k < services.size(); k++) {
					if (services.get(k).getName().equals(serviceName)) {
						service = services.get(k);
						guest.addGuestService(service);
					}
				}
			}

			guests.add(guest);

		}
		return guests;
	}

}
