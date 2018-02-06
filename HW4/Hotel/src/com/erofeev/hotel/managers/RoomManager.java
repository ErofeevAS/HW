package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.mylist.MyList;

public class RoomManager implements IManager {
	private MyList<Room> rooms = new MyList<Room>();
	
	

	public boolean add(Room room) {
		boolean flag = false;
		for (int i = 0; i < rooms.length(); i++) {
			if ((rooms.get(i).equals(room))) {
				flag = true;					
			}			
		}
		if(!flag){
			rooms.add(room);
		}
		return flag;
			
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
	
	public Room findExistingRoom(Room room) {
		Room findingRoom = null;
		for (int i = 0; i < rooms.length(); i++) {
			if (rooms.get(i).equals(room)) {
				findingRoom = rooms.get(i);
			}
		}
		return findingRoom;
	}

	@Override
	public String[] read() {
		MyList<Room> currentRooms = this.getRooms();
		String[] strRooms = new String[currentRooms.length() ];			

		for (int i = 0; i < currentRooms.length(); i++) {
			if (currentRooms.get(i) != null) {								
				strRooms[i] = "";
				strRooms[i] += currentRooms.get(i).toString();				
			}
		}
		return strRooms;
	}

}
