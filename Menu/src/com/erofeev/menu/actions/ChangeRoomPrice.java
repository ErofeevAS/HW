package com.erofeev.menu.actions;


import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Room;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ChangeRoomPrice implements IAction {
	private IReception model;	

	public ChangeRoomPrice(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
	
		String name = Viewer.findEntity("Room");
		Room room = model.findRoombyName(name);
		float price = Float.parseFloat(Viewer.changePrice());		
		model.changeRoomPrice(room, price);
		
	}

}