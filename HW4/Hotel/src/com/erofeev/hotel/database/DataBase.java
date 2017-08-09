package com.erofeev.hotel.database;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.erofeev.hotel.entity.*;

public class DataBase {
	// 0 - Room
	// 1 - Guest
	// 2- Service
	// 3 - Date
	// 4..6 - GuestsHistory

	private int roomCounter;
	private int guestCounter;
	private Object[][] db = new Object[8][20];

	public void incRoomCounter() {
		roomCounter++;
	}

	public void dicreaseRoomCounter() {
		roomCounter--;
	}

	public int getRoomCounter() {
		return roomCounter;
	}

	public void incGuestCounter() {
		guestCounter++;
	}

	public void dicreaseGuestCounter() {
		guestCounter--;
	}

	public int getGuestCounter() {
		return guestCounter;
	}

	public void addRoom(Room room) {
		db[0][getRoomCounter()] = room;
		incRoomCounter();
		System.out.println("Room added");

	}

	public int getRoomNumber() {
		int result = 0;
		for (int i = 0; i < db[0].length; i++) {
			result = i;
			if (db[0][i] == null) {
				break;
			}
		}
		return result;
	}

	public int getEmptyRoomNumber() {
		int result = 0;
		Room currentRoom;
		for (int i = 0; i < db[0].length; i++) {
			currentRoom = (Room) db[0][i];
			if ((currentRoom != null) && (currentRoom.isEmpty())) {
				result++;
			}
		}
		return result;
	}

	public int getRoomNumber(String date) {
		int result = 0;
		Date currentDate = null;
		Room currentRoom;
		Guest currentGuest;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			currentDate = dateFormat.parse(date);
		} catch (Exception e) {
			System.out.println("Worng data format");
		}

		for (int i = 0; i < db[0].length; i++) {
			currentRoom = (Room) db[0][i];
			currentGuest = (Guest) db[1][i];
			if ((currentRoom != null) && (currentRoom.isEmpty())) {
				result++;
			}
			if (((currentGuest != null)) && (((currentGuest.getOutDate()).getTime()) < (currentDate.getTime()))) {
				result++;
			}
		}
		return result;
	}

	public int getGuestsNumber() {
		int result = 0;
		for (int i = 0; i < db[0].length; i++) {
			if (db[1][i] != null) {
				result++;
			}
		}
		return result;
	}

	public Room getRoom(int index) {
		return (Room) db[0][index];
	}

	public Room[] getAllRooms() {
		Room[] rooms = new Room[getRoomNumber()];
		for (int i = 0; i < getRoomNumber(); i++) {
			rooms[i] = (Room) db[0][i];
		}
		return rooms;
	}

	public Room[] getAllEmptyRooms() {
		Room[] rooms = new Room[getEmptyRoomNumber()];

		Room currentRoom;
		int emptyRoomCounter = 0;
		for (int i = 0; i < getRoomNumber(); i++) {
			currentRoom = (Room) db[0][i];
			if (currentRoom.isEmpty()) {
				rooms[emptyRoomCounter] = currentRoom;
				emptyRoomCounter++;
			}
		}
		return rooms;
	}

	public Room[] getEmptyRoomsByDate(String date) {
		int index = 0;
		Date currentDate = null;
		Room currentRoom;
		Guest currentGuest;
		Room[] rooms = new Room[getRoomNumber(date)];

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			currentDate = dateFormat.parse(date);
		} catch (Exception e) {
			System.out.println("Worng data format");
		}

		for (int i = 0; i < db[0].length; i++) {
			currentRoom = (Room) db[0][i];
			currentGuest = (Guest) db[1][i];

			if ((currentRoom != null) && (currentRoom.isEmpty())) {
				rooms[index] = currentRoom;
				index++;
			}

			if (((currentGuest != null)) && (((currentGuest.getOutDate()).getTime()) < (currentDate.getTime()))) {
				rooms[index] = currentRoom;
				index++;
			}
		}
		return rooms;

	}

	public Guest[] getRoomHistory(Room room) {
		Guest[] guests = new Guest[3];
		guests[0] = (Guest) db[4][findRoom(room)];
		guests[1] = (Guest) db[5][findRoom(room)];
		guests[2] = (Guest) db[6][findRoom(room)];
		return guests;
	}

	public Guest[] getAllGuests() {
		Guest[] guests = new Guest[getGuestsNumber()];
		int index = 0;
		for (int i = 0; i < db[0].length; i++) {
			if (db[1][i] != null) {
				guests[index] = (Guest) db[1][i];
				index++;
			}
		}
		return guests;
	}

	public int findRoom(Room room) {
		int result = 0;
		if (room.equals(null)) {
			System.out.println("wrong argument");
		} else {
			for (int i = 0; i < db[0].length; i++) {
				if ((db[0][i]) == (null)) {
					break;
				}
				if ((db[0][i]).equals(room)) {
					result = i;
					break;
				} else {
					result = -1;
				}
			}
		}

		return result;
	}

	public int findGuest(Guest guest) {
		int result = 0;
		for (int i = 0; i < db[1].length; i++) {
			Guest currentGuest = (Guest) db[1][i];
			if ((currentGuest) == (guest)) {
				result = i;
				break;
			} else {
				result = -1;
			}
		}
		return result;
	}

	public void addGuest(Guest guest, Room room) {
		int index;
		if (room.isEmpty()) {
			room.setEmpty(false);
			index = findRoom(room);
			db[1][index] = guest;
			db[3][index] = guest.getOutDate();
			incRoomHistory(guest, index);
		} else {
			System.out.println("Room is busy");
		}
	}

	public Object[][] removeRoom(Room room) {
		boolean flag = true;
		for (int i = 0; i < db[0].length; i++) {
			if (db[0][i] == null) {
				break;
			}
			if (flag) {
				db[0][i] = db[0][i];
				db[1][i] = db[1][i];
				db[2][i] = db[2][i];
				db[3][i] = db[3][i];
				db[4][i] = db[4][i];
				db[5][i] = db[5][i];
				db[6][i] = db[6][i];
				if (db[0][i].equals(room)) {
					dicreaseRoomCounter();
					flag = false;
					if (!flag) {
						db[0][i] = db[0][i + 1];
						db[1][i] = db[1][i + 1];
						db[2][i] = db[2][i + 1];
						db[3][i] = db[3][i + 1];
						db[4][i] = db[4][i + 1];
						db[5][i] = db[5][i + 1];
						db[6][i] = db[6][i + 1];
					}
				}
			} else {
				db[0][i] = db[0][i + 1];
				db[1][i] = db[1][i + 1];
				db[2][i] = db[2][i + 1];
				db[3][i] = db[3][i + 1];
				db[4][i] = db[4][i + 1];
				db[5][i] = db[5][i + 1];
				db[6][i] = db[6][i + 1];
			}
		}
		return db;
	}

	public void removeGuest(Room room) {
		int index;
		index = findRoom(room);
		Room currentRoom = (Room) db[0][index];

		if (currentRoom.isEmpty()) {
			System.out.println("Room is empty");
		} else {
			db[1][index] = null;
			db[3][index] = 0;
			currentRoom.setEmpty(true);
		}
	}

	public void addSerivice(Guest guest, Service service) {
		int index;
		ServiceList serviceList;
		index = findGuest(guest);

		if (((ServiceList) db[2][index]) == null) {
			serviceList = new ServiceList();
			serviceList.addService(service);
		} else {
			serviceList = (ServiceList) db[2][index];
			serviceList.setServiceCounter(serviceList.getNotNull());
			serviceList.addService(service);
		}
		db[2][index] = serviceList;
	}

	public Service[] getGuestService(Guest guest) {
		int index;
		Service[] services = null;
		index = findGuest(guest);
		if (index != -1) {
			ServiceList serviceList = (ServiceList) db[2][index];
			services = new Service[serviceList.getNotNull()];
			for (int i = 0; i < services.length; i++) {
				services[i] = serviceList.getServiceList()[i];
			}
		}

		return services;
	}

	public Service[] getRoomService(Room room) {
		int index;
		Service[] services = null;
		index = findRoom(room);
		if (index != -1) {
			ServiceList serviceList = (ServiceList) db[2][index];
			services = new Service[serviceList.getNotNull()];
			for (int i = 0; i < services.length; i++) {
				services[i] = serviceList.getServiceList()[i];
			}
		}

		return services;
	}

	public float getServicesPrice(Guest guest) {
		float totalPrice = 0;
		int index;
		index = findGuest(guest);
		ServiceList serviceList = (ServiceList) db[2][index];
		totalPrice = serviceList.getAllPrice();

		return totalPrice;
	}

	public void incRoomHistory(Guest guest, int index) {
		db[6][index] = db[5][index];
		db[5][index] = db[4][index];
		db[4][index] = guest;
	}

	public Object[][] getDB() {
		return db;
	}

	public String[] parseLine() {
		StringBuilder str = new StringBuilder();
		Object[][] line = getDB();
		String[] data = new String[line.length];
		for (int i = 0; i < line.length; i++) {
			for (int j = 0; j < line[0].length; j++) {				
					if(line[i][j] instanceof Room){
						str.append((Room)line[i][j]);
					}
					if(line[i][j] instanceof Guest){
						str.append((Guest)line[i][j]);
					}
					
					if(line[i][j] instanceof Service){
						str.append((Service)line[i][j]);
					}
					
					if(line[i][j] instanceof ServiceList){
						str.append((ServiceList)line[i][j]);
					}
					else{
						str.append(line[i][j]);
					}										
				}
			
			
			data[i] = str.toString();
			str.delete(0, line[0].length);
		}

		return data;
	}

	

}
