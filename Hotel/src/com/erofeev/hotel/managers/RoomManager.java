package com.erofeev.hotel.managers;

import java.util.ArrayDeque;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;

public class RoomManager extends AbstractManager<Room> {
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private static final Logger loggerRoomManager = LogManager.getLogger(RoomManager.class);
	private int currentId = 0;

	public void add(Room room) {
		room.setID(currentId);
		currentId++;
		if (rooms.add(room)) {
			loggerRoomManager.info(room.toString() + " was added.");
		} else {
			loggerRoomManager.info(room.toString() + " already exists.");
			currentId--;
		}
	}

	public void add(ArrayList<Room> newRooms) {
		if (rooms.addAll(newRooms)) {
			loggerRoomManager.info(newRooms.toString() + " was added.");
		} else {
			loggerRoomManager.info(newRooms.toString() + " already exists.");
		}
		currentId = this.getMaxId();
	}

	public void remove(Room room) {
		if (rooms.remove(room)) {
			loggerRoomManager.info(room.toString() + " was removed.");
		} else {
			loggerRoomManager.info(room.toString() + " can't found room");
		}
	}

	public Room findbyName(String name) {
		Room foundEntity = null;
		for (int i = 0; i < rooms.size(); i++) {
			if ((((IEntity) rooms.get(i)).getName()).equals(name)) {
				foundEntity = rooms.get(i);
				break;
			}
		}
		return foundEntity;
	}

	public void changeRoomPrice(Room room, float price) {
		int index = rooms.indexOf(room);
		rooms.get(index).setPrice(price);
		loggerRoomManager.info(room.toString() + " price was changed.");
	}

	public void changeRoomStatus(Room room, boolean status) {
		int index = rooms.indexOf(room);
		rooms.get(index).setEmpty(status);
		loggerRoomManager.info(room.toString() + " status was changed.");
	}

	public ArrayList<Room> getEmptyRooms() {
		ArrayList<Room> emptyRooms = new ArrayList<Room>();
		for (int i = 0; i < getAll().size(); i++) {
			Room room = getAll().get(i);
			if (room.isEmpty()) {
				emptyRooms.add(room);
			}
		}
		return emptyRooms;
	}

	public ArrayDeque<Guest> getRoomHistory(Room room) {
		return room.getRoomHistory();
	}

	public ArrayList<Room> getAll() {
		return rooms;
	}

}
