package com.erofeev.menu.actions;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ChangeRoomStatus implements IAction {
	private IReception model;	

	public ChangeRoomStatus(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException, IllegalArgumentException {
		Printer.print("0 - REPAIRABLE");
		Printer.print("1 - SERVICED");		
	
		String name = Viewer.findEntity("Room");
		Room room = model.findRoombyName(name);
		String status = Viewer.changeStatus();
		
		
		switch(status){
		case "0":
			model.changeRoomStatus(room, RoomStatus.REPAIRABLE);
			break;
		case "1":
			model.changeRoomStatus(room, RoomStatus.SERVICED);
			break;
			
			default:
				throw new IllegalArgumentException();
		}
		
	}

}