package com.erofeev.menu.actions;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;

public class ViewAllGuests implements IAction {
	private IReception model;
	

	public ViewAllGuests(IReception model) {		
		this.model = model;
	}


	@Override
	public void execute() {		
		
		ArrayList<Guest> guests = model.getAllGuests();
		for (Guest guest : guests) {
			Printer.print(guest);
		
		}
		
	}



}
