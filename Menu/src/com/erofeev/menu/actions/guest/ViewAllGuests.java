package com.erofeev.menu.actions.guest;

import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
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
		List<Guest> guests = model.getAllGuests();
		for (Guest guest : guests) {
			Printer.print(guest);		
		}		
	}
}
