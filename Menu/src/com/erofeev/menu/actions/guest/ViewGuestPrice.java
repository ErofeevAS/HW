package com.erofeev.menu.actions.guest;


import java.io.IOException;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewGuestPrice implements IAction {
	private IReception model;
	
	public ViewGuestPrice(IReception model) {		
		this.model = model;
	}
	
	private void viewGuestPrice(float[] price) {
		Printer.print("servicesPrice: " + price[0]);
		Printer.print("roomPrice: " + price[1]);
		Printer.print("totalPrice: " + price[2]);
	}

	@Override
	public void execute() throws IOException {		
		String name = Viewer.findEntity("Guest");
		Guest guest = model.findGuestbyName(name);
		this.viewGuestPrice(model.getGuestPrice(guest));
	}

}