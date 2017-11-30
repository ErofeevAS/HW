package com.erofeev.menu.actions.room;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;

public class ViewAllEmptyRooms  implements IAction {	
	private IReception model;
	
	

	public ViewAllEmptyRooms(IReception model) {	
		this.model = model;
	}


	@Override
	public void execute() {		
		
		ArrayList<Room> rooms = model.getAllEmptyRooms();
		for (Room room : rooms) {
			Printer.print(room);
		}
		
	}



}
