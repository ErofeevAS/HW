package com.erofeev.hotel.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.erofeev.hotel.entity.*;
import com.erofeev.hotel.manager.*;

public class DataBase {
	private RoomManager[] roomsList = new RoomManager[100];
	private int roomCounter = 0;
	private int guestCounter = 0;

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

	public RoomManager[] getRoomsList() {
		return roomsList;
	}

	public int findRoom(Room room) {
		int result = 0;
		if (room.equals(null)) {
			System.out.println("wrong argument");
		} else {
			for (int i = 0; i < roomsList.length; i++) {
				if ((roomsList[i].getRoom()) == (null)) {
					break;
				}
				if ((roomsList[i].getRoom()).equals(room)) {
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
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				Guest currentGuest = roomsList[i].getGuest();
				if ((currentGuest) == (guest)) {
					result = i;
					break;
				} else {
					result = -1;
				}
			}
		}
		return result;
	}

	public int getEmptyRoomAmoount() {
		int result = 0;
		Room currentRoom;
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				currentRoom = roomsList[i].getRoom();
				if ((currentRoom != null) && (currentRoom.isEmpty())) {
					result++;
				}
			}
		}
		return result;
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

		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				currentRoom = roomsList[i].getRoom();
				currentGuest = roomsList[i].getGuest();

				if ((currentRoom != null) && (currentRoom.isEmpty())) {
					rooms[index] = currentRoom;
					index++;
				}

				if (((currentGuest != null)) && (((currentGuest.getOutDate()).getTime()) < (currentDate.getTime()))) {
					rooms[index] = currentRoom;
					index++;
				}
			}
		}
		return rooms;

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

		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				currentRoom = roomsList[i].getRoom();
				currentGuest = roomsList[i].getGuest();
				if ((currentRoom != null) && (currentRoom.isEmpty())) {
					result++;
				}
				if (((currentGuest != null)) && (((currentGuest.getOutDate()).getTime()) < (currentDate.getTime()))) {
					result++;
				}
			}
		}
		return result;
	}

	public int getGuestsNumber() {
		int result = 0;
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				if ((roomsList[i].getGuest()) != null) {
					result++;
				}
			}
		}
		return result;
	}

	public Room getRoom(int index) {
		return roomsList[index].getRoom();
	}

	public Guest[] getAllGuests() {
		Guest[] guests = new Guest[getGuestsNumber()];
		int index = 0;
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] != null) {
				if (roomsList[i].getGuest() != null) {
					guests[index] = roomsList[i].getGuest();
					index++;
				}
			}
		}
		return guests;
	}

	public Room[] getAllRooms() {
		Room[] rooms = new Room[getRoomCounter()];
		for (int i = 0; i < getRoomCounter(); i++) {
			rooms[i] = (Room) roomsList[i].getRoom();
		}
		return rooms;
	}

	public Room[] getAllEmptyRooms() {
		Room[] rooms = new Room[getEmptyRoomAmoount()];
		Room currentRoom;
		int emptyRoomCounter = 0;
		for (int i = 0; i < getRoomCounter(); i++) {
			currentRoom = roomsList[i].getRoom();
			if (currentRoom.isEmpty()) {
				rooms[emptyRoomCounter] = currentRoom;
				emptyRoomCounter++;
			}
		}
		return rooms;
	}

	public Service[] getGuestService(Guest guest) {
		int index;
		Service[] services = null;
		index = findGuest(guest);
		if (index != -1) {
			ServiceList serviceList = (ServiceList) roomsList[index].getServiceList();
			services = new Service[serviceList.getNotNull()];
			for (int i = 0; i < services.length; i++) {
				services[i] = serviceList.getServiceList()[i];
			}
		}
		return services;
	}

	public Guest[] getRoomHistory(Room room) {
		int index = findRoom(room);
		return roomsList[index].getRoomHistory();
	}

	public Service[] getRoomService(Room room) {
		int index;
		Service[] services = null;
		index = findRoom(room);
		if (index != -1) {
			if (roomsList[index].getServiceList() != null) {
				ServiceList serviceList = roomsList[index].getServiceList();
				services = new Service[serviceList.getNotNull()];
				for (int i = 0; i < services.length; i++) {
					services[i] = serviceList.getServiceList()[i];
				}
			} else {

			}
		}
		return services;
	}

	public float getServicesPrice(Guest guest) {
		float totalPrice = 0;
		int index;
		index = findGuest(guest);
		ServiceList serviceList = roomsList[index].getServiceList();
		totalPrice = serviceList.getAllPrice();
		return totalPrice;
	}

	public void addRoom(Room room) {
		RoomManager currentRoom = new RoomManager();
		currentRoom.setRoom(room);
		roomsList[getRoomCounter()] = currentRoom;
		incRoomCounter();
	}

	public void addGuest(Room room, Guest guest) {
		int roomIndex = findRoom(room);
		if (roomIndex != -1) {
			roomsList[roomIndex].setGuest(guest);
			incGuestCounter();
		} else {
			System.out.println("room not found");
		}
	}

	public void addService(Room room, Service service) {
		int index;
		ServiceList serviceList;
		index = findRoom(room);

		if ((roomsList[index].getServiceList()) == null) {
			serviceList = new ServiceList();
			serviceList.addService(service);
		} else {
			serviceList = roomsList[index].getServiceList();
			serviceList.setServiceCounter(serviceList.getNotNull());
			serviceList.addService(service);
		}
		roomsList[index].setServiceList(serviceList);
	}

	public void addSerivice(Guest guest, Service service) {
		int index;
		ServiceList serviceList;
		index = findGuest(guest);
		if (index == -1) {
		} else {
			if ((roomsList[index].getServiceList()) == null) {
				serviceList = new ServiceList();
				serviceList.addService(service);
			} else {
				serviceList = roomsList[index].getServiceList();
				serviceList.setServiceCounter(serviceList.getNotNull());
				serviceList.addService(service);
			}
			roomsList[index].setServiceList(serviceList);
		}
	}

	public RoomManager[] removeRoom(Room room) {
		boolean flag = true;
		for (int i = 0; i < roomsList.length; i++) {
			if (roomsList[i] == null) {
				break;
			}
			if (flag) {
				roomsList[i] = roomsList[i];
				if (roomsList[i].equals(room)) {
					dicreaseRoomCounter();
					flag = false;
					if (!flag) {
						roomsList[i] = roomsList[i + 1];
					}
				}
			} else {
				roomsList[i] = roomsList[i + 1];
			}
		}
		return roomsList;
	}

	public void removeGuest(Room room) {
		int index;
		index = findRoom(room);
		Room currentRoom =  roomsList[index].getRoom();
		if (currentRoom.isEmpty()) {
			System.out.println("Room is empty");
		} else {
			roomsList[index].removeGuest(null);
			currentRoom.setEmpty(true);
			System.out.println("Guest is evicted ");
		}
	}

	public String[] parseLine() {
		StringBuilder str = new StringBuilder();
		RoomManager[] line = getRoomsList();
		String[] data = new String[line.length];
		for (int i = 0; i < line.length; i++) {
			if (line[i] != null) {
				str.append(line[i].getRoom());
				str.append(" ");
				str.append(line[i].getGuest());
				str.append(" ");
				str.append(line[i].getServiceList());
				str.append(" ");
			}

			data[i] = str.toString();
			str.delete(0, line.length);
		}
		return data;
	}
}
