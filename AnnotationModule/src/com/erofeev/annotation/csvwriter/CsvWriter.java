package com.erofeev.annotation.csvwriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.annotation.dataobjects.EntityData;
import com.erofeev.annotation.dataobjects.FieldData;
import com.erofeev.annotation.handler.AnnotationsHandler;
import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.properties.Config;

public class CsvWriter {
	private static final Logger loggerCsvWriter = LogManager.getLogger(CsvWriter.class);
	static String path;
	static public String fileName;
	static public String separator;
	static public String servicesFileName;
	static public String roomsFileName;
	static public String guestsFileName;
	private final static String nestedSeparator = "\\|";
	
	
	private static String listToCSV(EntityData data) {
		List<FieldData> fields = data.getFields();
		String line = "";
		for (FieldData field : fields) {
			if (field.getKeyField().equals("def")) {
				line += field.getFieldValue() + separator;
			} else {
				line += field.getKeyField() + separator;
			}
		}
		return line;
	}

	public static void init() {
		List<String> params = Config.getConfig();
		path = params.get(5);
		servicesFileName = path + AnnotationsHandler.getEntityData(new Service()).getFileName();
		roomsFileName = path + AnnotationsHandler.getEntityData(new Room()).getFileName();
		guestsFileName = path + AnnotationsHandler.getEntityData(new Guest()).getFileName();
		separator = AnnotationsHandler.getEntityData(new Service()).getSeparator();
	}


	public static void write(List<EntityData> data) {
		init();
		if (data.size() != 0) {
			fileName = data.get(0).getFileName();
			separator = data.get(0).getSeparator();
			TextFileManager fileManager = new TextFileManager();
			String newLine = "";
			String[] lines = new String[data.size()];

			for (int i = 0; i < lines.length; i++) {
				newLine = listToCSV(data.get(i));
				lines[i] = newLine;
			}
			fileManager.writeToFile(path + fileName, lines);
		}
	}



	public static List<String> readLines(String fileName) {
		TextFileManager fileManager = new TextFileManager();
		return fileManager.readFromFile(fileName);
	}

	public static List<Service> parseCSVServices() throws NumberFormatException {
		List<String> fileString = readLines(servicesFileName);
		List<Service> services = new ArrayList<Service>();
		for (int i = 0; i < fileString.size(); i++) {
			String[] parts = null;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			String name = parts[1];
			Float price = Float.parseFloat(parts[2]);
			Service service = new Service(ID, name, price);
			services.add(service);
		}
		return services;
	}

	public static List<Guest> parseCSVGuests() throws NumberFormatException {
		List<String> fileString = readLines(guestsFileName);
		List<Guest> guests = new ArrayList<Guest>();
		List<Service> services = new ArrayList<Service>();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss z YYYY", Locale.ENGLISH);

		for (int i = 0; i < fileString.size(); i++) {
			String[] parts = null;
			Guest guest = null;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			String firstName = parts[1];
			String surName = parts[2];
			String roomID = parts[5];

			try {
				Date arrivalDate = dateFormat.parse((parts[3]));
				Date leavingDate = dateFormat.parse(parts[4]);
				guest = new Guest(firstName, surName, arrivalDate, leavingDate);
				guest.setId(ID);

			} catch (ParseException e) {
				loggerCsvWriter.warn("Can't parse date field");				
			}

			if (roomID.equals("null")) {

			} else {
				List<Room> rooms = parseCSVRooms();
				for (Room room2 : rooms) {
					if ((room2.getId()) == Integer.parseInt(roomID)) {
						guest.setRoom(room2);
					}
				}
			}

			if (parts[6].equals("null")) {

			} else {

				String[] servicesID = parts[6].split(nestedSeparator);
				List<Service> serv = parseCSVServices();
				for (int j = 0; j < servicesID.length; j++) {

					for (Service service : serv) {
						if ((service.getId()) == Integer.parseInt(servicesID[j])) {
							services.add(service);
						}
					}

				}
				guest.setGuestServices(services);
			}
			guests.add(guest);
		}
		return guests;
	}

	public static List<Room> parseCSVRooms() throws NumberFormatException {
		List<String> fileString = readLines(roomsFileName);
		List<Room> rooms = new ArrayList<Room>();
		List<Guest> roomHistory = new ArrayList<Guest>();
		for (int i = 0; i < fileString.size(); i++) {
			roomHistory.clear();
			String[] parts = null;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			int stars = Integer.parseInt(parts[1]);
			Float price = Float.parseFloat(parts[2]);
			int capacity = Integer.parseInt(parts[3]);
			String name = parts[5];

			RoomStatus status;
			if (parts[6].equals("SERVICED")) {
				status = RoomStatus.SERVICED;
			} else {
				status = RoomStatus.REPAIRABLE;
			}

			boolean empty;
			if (parts[4].equals("true")) {
				empty = true;
			} else {
				empty = false;
			}
			Room room = new Room(ID, name, stars, price, capacity, empty);
			room.setRoomStatus(status);
			String roomHistoryStr = parts[7];
			if (roomHistoryStr.equals("null")) {
			} else {
			}
			rooms.add(room);
		}
		return rooms;
	}

}
