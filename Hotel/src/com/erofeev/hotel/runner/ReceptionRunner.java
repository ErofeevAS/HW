package com.erofeev.hotel.runner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.reception.Reception;

public class ReceptionRunner {

	private static final Logger loggerRunner = LogManager.getLogger(ReceptionRunner.class);

	public static void main(String[] args) throws Exception {

		loggerRunner.info("first log");

		String GUESTS_FILE;
		String ROOMS_FILE;
		String SERVICES_FILE;
		if (args.length == 0) {

			GUESTS_FILE = "guests.txt";
			ROOMS_FILE = "rooms.txt";
			SERVICES_FILE = "services.txt";

		} else {

			GUESTS_FILE = args[0];
			ROOMS_FILE = args[1];
			SERVICES_FILE = args[2];

		}

		IReception reception = new Reception();
		// reception.initFileManager(ROOMS_FILE, GUESTS_FILE, SERVICES_FILE);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date arrivalDate1 = dateFormat.parse("2017-10-10");
		Date leavingDate1 = dateFormat.parse("2017-11-15");
		Date arrivalDate2 = dateFormat.parse("2017-8-23");
		Date leavingDate2 = dateFormat.parse("2017-8-25");
		Date arrivalDate3 = dateFormat.parse("2017-12-1");
		Date leavingDate3 = dateFormat.parse("2017-12-17");

		Guest guest1 = new Guest("Anka", "Pulimetchica", arrivalDate1, leavingDate1);
		Guest guest2 = new Guest("Vanka", "Vstanka", arrivalDate2, leavingDate2);
		Guest guest3 = new Guest("John", "Printer", arrivalDate3, leavingDate3);
		Guest guest4 = new Guest("Alice", "Priaaasdnter", arrivalDate3, leavingDate3);

		Service service1 = new Service("service#1", 100);
		Service service2 = new Service("service#2", 111);
		Service service3 = new Service("service#3", 32);
		Service service4 = new Service("service#4", 15);
		Service service5 = new Service("service#5", 765);
		Service service6 = new Service("service#6", 666);
		Service service7 = new Service("service#7", 1);

		Room room1 = new Room("1", 5, 666f, 3);
		Room room2 = new Room("2", 3, 2000f, 1);
		Room room3 = new Room("3", 8, 3000f, 2);
		Room room4 = new Room("4", 5, 199f, 2);
		Room room5 = new Room("5", 2, 222f, 2);

		// reception.addRoom(room5);
		// reception.addRoom(room1);

		// reception.removeRoom(room2);
		/*
		 * reception.addService(service1); reception.addService(service2);
		 * reception.addService(service3);
		 */
		// reception.addService(service4);

		// reception.removeService(service1);

		// reception.occupyGuest(guest2, room1);
		// reception.evictGuest(guest2,room1);

	}

}
