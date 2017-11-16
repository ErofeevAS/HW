
package com.erofeev.menu.actions;



import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class OccupyGuest implements IAction {
	private IReception model;
	

	public OccupyGuest(IReception model) {
		super();
		this.model = model;
	}


	@Override
	public void execute() throws IllegalArgumentException, IOException{		
		String roomName = Viewer.findEntity("Room");
		Room room = model.findRoombyName(roomName);
		Guest guest = Viewer.createGuest();
		model.occupyGuest(guest, room);	}

}
