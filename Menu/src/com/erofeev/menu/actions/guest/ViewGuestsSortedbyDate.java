package com.erofeev.menu.actions.guest;

import java.util.ArrayList;
import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewGuestsSortedbyDate implements IAction {

	private IReception model;
	public ViewGuestsSortedbyDate(IReception model) {		
		this.model = model;
	}

	public void viewGuestSortedByDate(List<Guest> guests) {
		Printer.print("Guests sorted by date: ");
		Viewer.viewCmd(guests);

	}

	@Override
	public void execute() {		
		List<Guest> guests = model.getGuestSortedByData();
		this.viewGuestSortedByDate(guests);
	}
}
