package com.erofeev.hotel.reception;

import java.util.Arrays;

import com.erofeev.hotel.database.DataBase;
import com.erofeev.hotel.entity.*;
import com.erofeev.hotel.sort.*;
import com.erofeev.hotel.manager.*;
import com.erofeev.hotel.sort.RoomsSortedByCapacity;

public class Reception {

	public void viewAllRoom(DataBase database) {
		Room[] rooms = database.getAllRooms();
		System.out.println("All rooms:");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void viewRoomsNumber(DataBase database) {
		System.out.println("Total rooms in hotel: " + database.getRoomCounter());
	}

	public void viewEmptyRoomsNumber(DataBase database) {
		System.out.println("Empty rooms number: " + database.getEmptyRoomAmoount());
	}

	public void viewGuestsNumber(DataBase database) {
		System.out.println("Guest numbers: " + database.getGuestsNumber());
	}

	public void viewRoomHistory(DataBase database, Room room) {
		Guest[] guestsHistory = new Guest[3];
		guestsHistory = database.getRoomHistory(room);
		System.out.println("Rooms History :");
		for (int i = 0; i < guestsHistory.length; i++) {
			System.out.println(guestsHistory[i]);
		}
	}

	public void viewAllPrice(DataBase database, Guest guest) {
		float price;
		if ((database.findGuest(guest)) == -1) {
			System.out.println("Guest not exist");
		} else {
			Room currentRoom = database.getRoom(database.findGuest(guest));
			currentRoom.getPrice();
			price = currentRoom.getPrice() * (guest.getOutDate().getTime() - guest.getInDate().getTime()) / 86400000;
			System.out.println("Guest price: " + price + ". Services price:" + database.getServicesPrice(guest));
		}
	}

	public void viewAllGuest(DataBase database) {

		RoomManager[] myDB = database.getRoomsList();
		for (int i = 0; i < myDB.length; i++) {
			if ((myDB[i] != (null))) {
				if (myDB[i].getGuest() != null) {
					System.out.println(myDB[i].getGuest());
				}
			}
		}
	}

	public void viewGuestService(DataBase database, Guest guest) {
		if (database.findGuest(guest) == -1) {
			System.out.println("Guest not exist");
		} else {
			Service[] services = database.getGuestService(guest);
			System.out.println(guest.getFio() + ". ServiceList: ");
			for (int i = 0; i < services.length; i++) {
				System.out.println(services[i]);
			}
		}
	}

	public void viewRoomDetails(DataBase database, Room room) {
		Service[] services = database.getRoomService(room);
		System.out.println("Room ¹" + room.getName() + ". ServiceList: ");
		if (services != null) {
			for (int i = 0; i < services.length; i++) {
				System.out.println(services[i]);
			}
		} else {
			System.out.println("Services List is empty");
		}
	}

	public void viewAllEmptyRoomByDate(DataBase database, String date) {
		System.out.println("Rooms that will be available by date :");
		Room[] rooms = database.getEmptyRoomsByDate(date);
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortGuestServicetByPrice(DataBase database, Guest guest) {
		Service[] services;
		services = database.getGuestService(guest);
		if (services == null) {
			System.out.println("Servies: absent");
		} else {
			System.out.println("Services sorted by Price: ");
			Arrays.sort(services, new ServiceSortedByPrice());
			for (int i = 0; i < services.length; i++) {
				System.out.println(services[i]);
			}
		}
	}

	public void sortGuestServicetByDate(DataBase database, Guest guest) {
		Service[] services;
		services = database.getGuestService(guest);
		if (services == null) {
			System.out.println("Servies: absent");
		} else {
			System.out.println("Services sorted by Date: ");
			Arrays.sort(services, new ServiceSortedByDate());
			for (int i = 0; i < services.length; i++) {
				System.out.println(services[i]);
			}
		}
	}

	public void sortRoomsByCapacity(DataBase database) {
		Room[] rooms;
		rooms = database.getAllRooms();
		Arrays.sort(rooms, new RoomsSortedByCapacity());
		System.out.println("Rooms sorted by capacity: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortEmptyRoomsByCapacity(DataBase database) {
		Room[] rooms;
		rooms = database.getAllEmptyRooms();
		Arrays.sort(rooms, new RoomsSortedByCapacity());
		System.out.println("Empty rooms sorted by capacity: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortRoomsByPrice(DataBase database) {
		Room[] rooms;
		rooms = database.getAllRooms();
		Arrays.sort(rooms, new RoomsSortedByPrice());
		System.out.println("Room sorted by price: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortEmptyRoomsByPrice(DataBase database) {
		Room[] rooms;
		rooms = database.getAllEmptyRooms();
		Arrays.sort(rooms, new RoomsSortedByPrice());
		System.out.println("Empty rooms sorted by price: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortRoomsByStars(DataBase database) {
		Room[] rooms = database.getAllRooms();
		Arrays.sort(rooms, new RoomsSortedByStars());
		System.out.println("Rooms sorted by stars: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortEmptyRoomsByStars(DataBase database) {
		Room[] rooms = database.getAllEmptyRooms();
		Arrays.sort(rooms, new RoomsSortedByStars());
		System.out.println("Empty rooms sorted by stars: ");
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
		}
	}

	public void sortGuestByName(DataBase database) {
		Guest[] guests = database.getAllGuests();
		guests = database.getAllGuests();
		System.out.println("Guests sorted by name: ");
		Arrays.sort(guests, new GuestSotredByName());
		for (int i = 0; i < guests.length; i++) {
			System.out.println(guests[i] + ". Room ¹:" + database.getRoom(database.findGuest(guests[i])).getName());
		}
	}

	public void sortGuestByDate(DataBase database) {
		Guest[] guests = database.getAllGuests();
		guests = database.getAllGuests();
		System.out.println("Guests sorted by date: ");
		Arrays.sort(guests, new GuestSortedByData());
		for (int i = 0; i < guests.length; i++) {
			System.out.println(guests[i] + ". Room ¹:" + database.getRoom(database.findGuest(guests[i])).getName());
		}
	}

	public void occupyRoom(DataBase database, Guest guest, Room room) {
		database.addGuest(room, guest);
	}

	public void evicRoom(DataBase database, Room room) {
		database.removeGuest(room);
	}

	public void changeRoomStatus(DataBase database, Room room, boolean status) {
		if (status) {
			database.addRoom(room);
		} else {
			database.removeRoom(room);
		}
	}

	public void addRoom(DataBase database, Room room) {
		database.addRoom(room);
		System.out.println("Room added");
	}

	public void addService(DataBase database, Guest guest, Service service) {
		database.addSerivice(guest, service);
	}

	public void changeRoomPrice(DataBase database, Room room, float price) {
		int index;
		index = database.findRoom(room);
		if (index == -1) {
			System.out.println("Room absent");
		} else {
			Room currentRoom = database.getRoom(index);
			currentRoom.setPrice(price);
		}
	}

	public void changeServicePrice(DataBase database, Guest guest, Service service, float price) {
		Service[] serviceList = database.getGuestService(guest);
		for (int i = 0; i < database.getGuestService(guest).length; i++) {
			if ((serviceList[i]).equals(service)) {
				(serviceList[i]).setPrice(price);
			}
		}
	}

	public String[] saveToFile(DataBase database) {
		return database.parseLine();
	}
}
