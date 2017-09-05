package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.mylist.MyList;

public class RoomManager implements IManager {
	private MyList<Room> rooms = new MyList<Room>();

	public void add(Room room) {
		rooms.add(room);
		System.out.println("Room: " + room + " was added");
	}

	public void remove(Room room) {
		System.out.println("Room: " + room + " was removed");
		rooms.remove(room);

	}

	public void changeRoomPrice(Room room, float price) {
		int index = rooms.find(room);
		(rooms.get(index)).setPrice(price);
	}

	public void changeRoomStatus(Room room, boolean status) {
		int index = rooms.find(room);
		(rooms.get(index)).setEmpty(status);
	}

	public MyList<Room> getRooms() {
		return rooms;
	}

	public MyList<Room> getEmptyRooms() {
		MyList<Room> emptyRooms = new MyList<Room>();

		for (int i = 0; i < getRooms().length(); i++) {
			Room room = getRooms().get(i);
			if (room.isEmpty()) {
				emptyRooms.add(room);
			}
		}
		return emptyRooms;
	}

	@Override
	public String[] read() {
		MyList<Room> currentRooms = this.getRooms();
		String[] strRooms = new String[currentRooms.length() + 1];
		StringBuilder str = new StringBuilder();
		str.append("Rooms: ");
		strRooms[0] = "";
		strRooms[0] += str.toString();		

		for (int i = 0; i < currentRooms.length(); i++) {
			if (currentRooms.get(i) != null) {
				str.delete(0, str.capacity());
				str.append(currentRooms.get(i));
				strRooms[i + 1] = "";
				strRooms[i + 1] += str.toString();
				;
			}
		}
		return strRooms;
	}

}
