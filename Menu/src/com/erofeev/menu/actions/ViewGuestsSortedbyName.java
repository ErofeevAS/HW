package com.erofeev.menu.actions;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewGuestsSortedbyName implements IAction {

	private IReception model;
	public ViewGuestsSortedbyName(IReception model) {		
		this.model = model;
	}

	public void viewGuestSortedByName(ArrayList<Guest> guests) {
		Printer.print("Guests sorted by name: ");
		Viewer.viewCmd(guests);

	}

	@Override
	public void execute() {
		
		ArrayList<Guest> guests = model.getGuestSortedByName();
		this.viewGuestSortedByName(guests);

	}

}
