package com.erofeev.hotel.runner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.danco.training.*;
import com.erofeev.hotel.database.DataBase;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.reception.Reception;
import com.erofeev.hotel.manager.*;

public class ReceptionRunner {

	public static void main(String[] args) throws Exception {

		DataBase db = new DataBase();
		Reception reception = new Reception();

		Room room1 = new Room(1, 5, 1000f, 3);
		Room room2 = new Room(2, 1, 155f, 1);
		Room room3 = new Room(4, 3, 666f, 5);
		Room room4 = new Room(3, 7, 777f, 3);

		Guest guest1 = new Guest("Ivanov Ivan", "2017-12-03", "2017-12-01");
		Guest guest2 = new Guest("Petrov Petr", "2016-12-03", "2016-12-05");
		Guest guest3 = new Guest("Beli Alex", "2017-12-03", "2014-12-03");
		Guest guest4 = new Guest("Stark Rob", "2015-12-03", "2017-12-12");

		Service serv1 = new Service("breakfast", 1, "2017-12-01");
		Service serv2 = new Service("bar", 10, "2017-12-05");
		Service serv3 = new Service("additional cleaning", 2, "2017-12-03");

		RoomManager rm = new RoomManager();
		rm.setRoom(room1);
		System.out.println(rm.getRoom());

		reception.addRoom(db, room1);
		reception.addRoom(db, room2);
		reception.addRoom(db, room3);
		reception.addRoom(db, room4);

		// reception.occupyRoom(db, guest1, room1);
		reception.occupyRoom(db, guest2, room4);
		// reception.occupyRoom(db, guest3, room2);
		reception.occupyRoom(db, guest4, room3);

		reception.viewAllRoom(db);

		System.out.println();
		reception.sortRoomsByCapacity(db);
		System.out.println();
		reception.sortRoomsByPrice(db);
		System.out.println();
		reception.sortRoomsByStars(db);
		System.out.println();
		reception.viewAllGuest(db);
		System.out.println();

		reception.sortEmptyRoomsByCapacity(db);
		System.out.println();
		reception.sortEmptyRoomsByPrice(db);
		System.out.println();
		reception.sortEmptyRoomsByStars(db);
		System.out.println();

		reception.viewGuestsNumber(db);
		reception.viewRoomsNumber(db);
		reception.viewEmptyRoomsNumber(db);
		System.out.println();

		reception.sortGuestByName(db);
		System.out.println();
		reception.sortGuestByDate(db);
		System.out.println();

		reception.viewAllEmptyRoomByDate(db, "2015-11-01");

		System.out.println();

		reception.viewRoomHistory(db, room1);
		reception.evicRoom(db, room1);
		reception.occupyRoom(db, guest2, room1);

		reception.viewRoomHistory(db, room1);
		reception.evicRoom(db, room1);
		reception.occupyRoom(db, guest3, room1);
		reception.viewRoomHistory(db, room1);
		reception.evicRoom(db, room1);
		reception.occupyRoom(db, guest1, room1);
		reception.viewRoomHistory(db, room1);
		reception.evicRoom(db, room1);

		reception.viewRoomHistory(db, room1);

		reception.viewAllRoom(db);
		reception.viewAllGuest(db);
		reception.evicRoom(db, room1);

		reception.changeRoomStatus(db, room1, false);
		System.out.println();
		reception.viewAllRoom(db);
		reception.viewAllGuest(db);

		reception.addService(db, guest4, serv2);
		reception.addService(db, guest4, serv1);
		reception.addService(db, guest4, serv3);

		reception.addService(db, guest2, serv2);
		reception.viewGuestService(db, guest1);

		reception.viewAllPrice(db, guest2);
		reception.viewAllPrice(db, guest4);

		reception.viewGuestService(db, guest4);

		reception.sortGuestServicetByDate(db, guest4);
		reception.sortGuestServicetByPrice(db, guest4);
		reception.sortGuestServicetByPrice(db, guest2);

		System.out.println();
		reception.viewAllRoom(db);
		System.out.println();
		reception.changeRoomPrice(db, room1, 3232323);
		System.out.println();
		reception.viewAllRoom(db);

		System.out.println();
		reception.viewGuestService(db, guest4);
		System.out.println();
		reception.changeServicePrice(db, guest4, serv3, 666);
		reception.viewGuestService(db, guest2);
		System.out.println();
		reception.viewRoomDetails(db, room4);

		String TEST_FILE;
		if (args.length == 0) {
			TEST_FILE = "data.txt";
		} else {
			TEST_FILE = args[0];
		}

		final String[] testValues = reception.saveToFile(db);

		Path filePath = Paths.get(TEST_FILE);
		Files.deleteIfExists(filePath);
		Files.createFile(filePath);

		try {
			TextFileWorker fileWorker = new TextFileWorker(TEST_FILE);
			fileWorker.writeToFile(testValues);
			Object[] readedValues = fileWorker.readFromFile();

			for (int i = 0; i < testValues.length; i++) {
				if (!readedValues[i].equals(testValues[i])) {
					throw new RuntimeException("Error. Not equal values: " + readedValues[i] + " and " + testValues[i]);
				}
			}
		} finally {

		}

	}

}
