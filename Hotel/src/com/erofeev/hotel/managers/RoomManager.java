package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.mylist.MyList;

public class RoomManager implements IManager {
	private MyList<Room> rooms = new MyList<Room>();

	public void add(Room room) {
		rooms.add(room);		
	}

	public void remove(Room room) {		
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
		String[] strRooms = new String[currentRooms.length() ];
		StringBuilder str = new StringBuilder();			

		for (int i = 0; i < currentRooms.length(); i++) {
			if (currentRooms.get(i) != null) {
				str.delete(0, str.capacity());
				str.append(currentRooms.get(i).getName());
				str.append(" ");
				str.append(currentRooms.get(i).getStars());
				str.append(" ");
				str.append(currentRooms.get(i).getPrice());
				str.append(" ");
				str.append(currentRooms.get(i).getCapacity());
				str.append(" ");
				str.append(currentRooms.get(i).isEmpty());				
				strRooms[i] = "";
				strRooms[i] += str.toString();				
			}
		}
		return strRooms;
	}

}
