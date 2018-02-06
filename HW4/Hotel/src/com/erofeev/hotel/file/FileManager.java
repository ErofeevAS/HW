package com.erofeev.hotel.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.danco.training.TextFileWorker;
import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.managers.GuestManager;
import com.erofeev.hotel.managers.RoomManager;
import com.erofeev.hotel.managers.ServicesManager;
import com.erofeev.hotel.mylist.MyList;
import com.erofeev.hotel.reception.Reception;

public class FileManager {

	private static volatile FileManager instance;
	private String roomsPath;
	private String guestsPath;
	private String servicesPath;

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
				}
			}
		}
		return localInstance;
	}

	public void initFileManager(String ROOMS_FILE, String GUESTS_FILE, String SERVICES_FILE) {
		this.roomsPath = ROOMS_FILE;
		this.guestsPath = GUESTS_FILE;
		this.servicesPath = SERVICES_FILE;
	}

	public void saveToFile(IManager manager) throws IOException {
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
		Path filePathGuests = Paths.get(FILE_PATH);
		Files.deleteIfExists(filePathGuests);
		Files.createFile(filePathGuests);

		try {
			TextFileWorker fileManager = new TextFileWorker(FILE_PATH);
			fileManager.writeToFile(strManager);

		} finally {
		}

	}

	public MyList<Room> readRoomsFromFile() {
		String ROOMS_FILE = this.getRoomsPath();
		MyList<Room> rooms = new MyList<Room>();
		TextFileWorker fileWorker = new TextFileWorker(ROOMS_FILE);
		String[] readedValues = fileWorker.readFromFile();

		int roomName = 0;
		int roomStars = 0;
		float roomPrice = 0;
		int roomCapacity = 0;
		boolean empty = true;
		Room room = null;

		for (int i = 0; i < readedValues.length; i++) {
			if ((readedValues[i] != null)) {				
				String[] strValue = readedValues[i].split(" ");
				roomName = Integer.parseInt(strValue[0]);
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
				room = new Room(roomName, roomStars, roomPrice, roomCapacity);
				room.setEmpty(empty);
				rooms.add(room);
			}
		}
		return rooms;

	}

	public MyList<Service> readServicesFromFile() {
		String SERVICES_FILE = this.getServicesPath();
		MyList<Service> services = new MyList<Service>();
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

	public MyList<Guest> readGuestFromFile(Reception reception) throws Exception {
		String GUESTS_FILE = this.getGuestsPath();
		MyList<Guest> guests = new MyList<Guest>();
		TextFileWorker fileWorker = new TextFileWorker(GUESTS_FILE);
		String[] readedValues = fileWorker.readFromFile();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		int roomName = 0;
		String guestFirstName = "";
		String guestSecondName = "";
		String serviceName = "";
		Date arrivalDate = null;
		Date leavingDate = null;
		Room room  = null ;
		Guest guest = null;
		Service service = null;

		for (int i = 0; i < readedValues.length; i++) {

			String[] strValue = readedValues[i].split(" ");
			guestFirstName = strValue[0];
			guestSecondName = strValue[1];
			arrivalDate = dateFormat.parse(strValue[2]);
			leavingDate = dateFormat.parse(strValue[3]);
			guest = new Guest(guestFirstName, guestSecondName, arrivalDate, leavingDate);

			roomName = Integer.parseInt(strValue[4]);
			MyList<Room> rooms = reception.getRoomManager().getRooms();
			for (int k = 0; k < rooms.length(); k++) {
				if (rooms.get(k).getName() == roomName) {
					room = rooms.get(k);
				}
				
			}
			room.setEmpty(false);
			guest.setRoom(room);				
			for (int j = 5; j < strValue.length; j++) {				
				serviceName = strValue[j];				
				MyList<Service> services = reception.getServicesManager().getServices();
				for (int k = 0; k < services.length(); k++) {					
					if (services.get(k).getName().equals(serviceName) ) {
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
