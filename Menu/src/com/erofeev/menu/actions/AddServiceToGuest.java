package com.erofeev.menu.actions;

import java.io.IOException;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class AddServiceToGuest implements IAction {
	private IReception model;
	

	public AddServiceToGuest(IReception model) {
		super();
		this.model = model;
	}


	@Override
	public void execute() throws IOException {		
		String guestName = Viewer.findEntity("Guest");
		Guest guest = model.findGuestbyName(guestName);	
		String serviceName = Viewer.findEntity("Service");
		Service service = model.findServicebyName(serviceName);	
		System.out.println(guest);
		System.out.println(service);
		model.addServiceToGuest(guest, service);
	}		

}