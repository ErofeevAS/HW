package com.erofeev.menu.actions.room;

import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewRoomsSortedByCapacity implements IAction {

	private IReception model;	

	public ViewRoomsSortedByCapacity(IReception model) {		
		this.model = model;
	}

	public void viewRoomsSortedByCapacity(List<Room> rooms) {
		Printer.print("Rooms sorted by capacity: ");
		Viewer.viewCmd(rooms);
	}

	@Override
	public void execute() {		
		List<Room> rooms = model.getRoomsSortedByCapacity();
		this.viewRoomsSortedByCapacity(rooms);

	}

}
