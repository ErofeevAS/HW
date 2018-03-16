package com.erofeev.menu.actions.room;

import java.io.IOException;
import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewRoomHistory implements IAction {
	private IReception model;

	public ViewRoomHistory(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Room");
		Room room = model.findRoombyName(name);
		List<Guest> roomHistory = model.getRoomHistory(room);
		for (Guest guest : roomHistory) {
			Printer.print(guest);
		}

	}

}
