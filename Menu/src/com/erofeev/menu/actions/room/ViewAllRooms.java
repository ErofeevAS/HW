package com.erofeev.menu.actions.room;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;

public class ViewAllRooms implements IAction {	
	private IReception model;
	
	

	public ViewAllRooms(IReception model) {	
		this.model = model;
	}



	@Override
	public void execute() {
		//model =  Commands.getInstance().getModel();
		
		ArrayList<Room> rooms = model.getAllRooms();
		for (Room room : rooms) {
			Printer.print(room);
		}
		
	}



}
