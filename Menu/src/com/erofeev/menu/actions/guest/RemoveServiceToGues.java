package com.erofeev.menu.actions.guest;

import java.io.IOException;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class RemoveServiceToGues implements IAction {
	private IReception model;	

	public RemoveServiceToGues(IReception model) {		
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String nameGuest = Viewer.findEntity("Guest");
		Guest guest = model.findGuestbyName(nameGuest);
		String nameService = Viewer.findEntity("Services");
		Service service = model.findServicebyName(nameService);
		model.removeServiceToGuest(guest, service);
	}
}
