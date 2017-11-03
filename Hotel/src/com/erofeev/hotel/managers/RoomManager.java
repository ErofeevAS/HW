package com.erofeev.hotel.managers;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.mylist.MyList;


public class RoomManager extends AbstractManager<Room>  {
	private MyList<Room> rooms = getEntities();
	private static final Logger LoggerRoomManager = LogManager.getLogger(RoomManager.class);

	
	public boolean add(Room room) {
		if (super.add(room)) {			
			return true;
		} else {			
			LoggerRoomManager.info(room.toString()  + " was added.");
			return false;
		}
	}
	
	public void remove(Room room){
		super.remove(room);
		LoggerRoomManager.info(room.toString()  + " was removed.");
	}

	public void changeRoomPrice(Room room, float price) {		
		int index = rooms.find(room);
		rooms.get(index).setPrice(price);
		LoggerRoomManager.info(room.toString()  + " price was changed.");
		
	}

	public void changeRoomStatus(Room room, boolean status) {
		int index = rooms.find(room);
		rooms.get(index).setEmpty(status);
		LoggerRoomManager.info(room.toString()  + " status was changed.");
	}


	public MyList<Room> getEmptyRooms() {
		MyList<Room> emptyRooms = new MyList<Room>();
		for (int i = 0; i < getAll().length(); i++) {
			Room room = getAll().get(i);
			if (room.isEmpty()) {
				emptyRooms.add(room);
			}
		}
		return emptyRooms;
	}

	

}
