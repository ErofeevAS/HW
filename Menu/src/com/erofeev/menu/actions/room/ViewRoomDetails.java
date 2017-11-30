package com.erofeev.menu.actions.room;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewRoomDetails implements IAction {

	private IReception model;

	public ViewRoomDetails(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Room");
		Room room = model.findRoombyName(name);
		Printer.print(room);

	}

}
