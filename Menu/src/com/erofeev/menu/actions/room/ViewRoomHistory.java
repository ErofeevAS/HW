package com.erofeev.menu.actions.room;

import com.erofeev.hotel.api.IReception;
import com.erofeev.menu.api.IAction;

public class ViewRoomHistory implements IAction {

	private IReception model;
	
	public ViewRoomHistory(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
