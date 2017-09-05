package com.erofeev.hotel.runner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.reception.Reception;

public class ReceptionRunner {
	public static void main(String[] args) throws Exception {

		String TEST_FILE;
		if (args.length == 0) {
			TEST_FILE = "data.txt";
		} else {
			TEST_FILE = args[0];
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date arrivalDate1 = dateFormat.parse("2017-12-10");
		Date leavingDate1 = dateFormat.parse("2017-12-15");
		Date arrivalDate2 = dateFormat.parse("2017-8-9");
		Date leavingDate2 = dateFormat.parse("2017-8-11");
		Date arrivalDate3 = dateFormat.parse("2017-12-1");
		Date leavingDate3 = dateFormat.parse("2017-12-17");

		Guest guest1 = new Guest("Vaska", "Pupkin", arrivalDate1, leavingDate1);
		Guest guest2 = new Guest("Petr", "Hacker", arrivalDate2, leavingDate2);
		Guest guest3 = new Guest("Tom", "Begemot", arrivalDate3, leavingDate3);

		Service service1 = new Service("service#1", 100);
		Service service2 = new Service("service#2", 111);
		Service service3 = new Service("service#3", 32);
		Service service4 = new Service("service#4", 15);
		Service service5 = new Service("service#5", 765);

		Room room1 = new Room(1, 5, 1000f, 3);
		Room room2 = new Room(2, 3, 2000f, 1);
		Room room3 = new Room(3, 8, 3000f, 2);

		Reception reception = new Reception();		
		
		reception.addService(service1);
		reception.addService(service2);
		reception.addService(service3);
		reception.addService(service4);
		reception.addService(service5);		

		reception.addRoom(room1);
		reception.addRoom(room2);
		reception.addRoom(room3);
		reception.changeRoomPrice(room1, 666f);

		reception.viewAllRooms();
		reception.viewAllEmptyRooms();

		reception.occupyGuest(guest1, room2);

		reception.viewAllRooms();
		reception.viewAllEmptyRooms();

		reception.evicGuest(guest1);
		reception.viewAllEmptyRooms();
		reception.occupyGuest(guest2, room3);
		reception.occupyGuest(guest3, room1);

		reception.viewAllGuests();
		reception.viewAllServices();
		
		reception.addServiceToGuest(guest2, service1);
		reception.addServiceToGuest(guest2, service3);
		reception.addServiceToGuest(guest2, service5);		
		
		System.out.println("Guest service:");
		reception.viewGuestServices(guest2);
		reception.removeServiceToGuest(guest2, service3);
		System.out.println("Guest service:");
		reception.viewGuestServices(guest2);
		reception.viewRoomDetails(room3);
		// reception.viewGuestPrice(guest2);
		reception.occupyGuest(guest1, room2);

		reception.viewAllRooms();
		reception.viewRoomsSortedByCapacity();
		reception.viewRoomsSortedByStars();
		reception.viewRoomsSortedByPrice();
		// MyList<Guest> list = guestManager.getAllGuest();

		reception.viewGuestSortedByData();
		reception.viewGuestSortedByName();

		reception.viewServiceSortedByPrice();
		reception.viewAllGuests();

		reception.saveToFile(TEST_FILE);
		reception.evicGuest(guest1);
		reception.occupyGuest(guest2, room2);
		reception.evicGuest(guest2);
		reception.occupyGuest(guest3, room2);
		reception.evicGuest(guest3);
		reception.viewAllGuests();

		// reception.viewRoomHistory(room1);
		reception.viewRoomHistory(room2);
		// reception.viewRoomHistory(room3);

	}

}
