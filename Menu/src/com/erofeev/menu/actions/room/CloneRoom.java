package com.erofeev.menu.actions.room;

import java.io.IOException;
import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class CloneRoom implements IAction {
	private IReception model;

	public CloneRoom(IReception model) {
		this.model = model;
	}

	private Room cloningRoom(Room room) throws IOException {

		try {
			Room newRoom = room.clone();
			return newRoom;
		} catch (CloneNotSupportedException e) {
			Printer.print("can't clone object " + e);
		}
		return null;

	}
	
	private Room getRoomByName() throws IOException {
		String name = Viewer.findEntity("Room");
		Room room = model.findRoombyName(name);
		return room;
	}

	private Room modifyRoom() throws IOException {
		Room room = this.getRoomByName();
		Room newRoom = this.cloningRoom(room);
		List<String> newParameters = Viewer.modifyRoom();
		String number = newParameters.get(0);
		String stars = newParameters.get(1);
		String price = newParameters.get(2);
		String capacity = newParameters.get(3);

		if (!number.equals("null")) {
			newRoom.setName(number);
		}
		if (!stars.equals("null")) {
			newRoom.setStars(Integer.parseInt(stars));
		}
		if (!price.equals("null")) {

			newRoom.setPrice(Float.parseFloat(price));
		}
		if (!capacity.equals("null")) {
			newRoom.setCapacity((Integer.parseInt(capacity)));
		}
		//newRoom.setRoomHistory(null);
		return newRoom;
	}
	

	@Override
	public void execute() throws IOException, IllegalArgumentException {
		model.addRoom(this.modifyRoom());

	}

}