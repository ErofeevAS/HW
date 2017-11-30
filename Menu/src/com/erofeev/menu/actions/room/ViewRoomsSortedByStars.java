package com.erofeev.menu.actions.room;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewRoomsSortedByStars implements IAction {

	private IReception model;
	

	public ViewRoomsSortedByStars(IReception model) {
		super();
		this.model = model;
	}

	public void viewRoomsSortedByStars(ArrayList<Room> rooms) {
		Printer.print("Rooms sorted by stars: ");
		Viewer.viewCmd(rooms);
	}

	@Override
	public void execute() {		
		ArrayList<Room> rooms = model.getRoomsSortedByStars();
		this.viewRoomsSortedByStars(rooms);

	}

}
