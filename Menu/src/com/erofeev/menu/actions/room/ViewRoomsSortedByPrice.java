package com.erofeev.menu.actions.room;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;


public class ViewRoomsSortedByPrice implements IAction {

	private IReception model;
	

	public ViewRoomsSortedByPrice(IReception model) {
		super();
		this.model = model;
	}

	public void viewRoomsSortedByPrice(ArrayList<Room> rooms) {
		Printer.print("Rooms sorted by price: ");
		Viewer.viewCmd(rooms);

	}

	@Override
	public void execute() {		
		ArrayList<Room> rooms = model.getRoomsSortedByPrice();
		this.viewRoomsSortedByPrice(rooms);

	}

}
