package com.erofeev.menu.actions.room;

import java.io.IOException;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class RemoveRoom implements IAction {
	private IReception model;	

	public RemoveRoom(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Room");		
		model.removeRoom(model.findRoombyName(name));		
	}
}