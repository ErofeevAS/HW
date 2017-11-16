package com.erofeev.menu.actions;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewServiceSortedByPrice implements IAction{

	private IReception model;
	
	

	public ViewServiceSortedByPrice(IReception model) {
		super();
		this.model = model;
	}

	public void viewGuestSortedByName(ArrayList<Service> services) {
		Printer.print("Services sorted by price: ");
		Viewer.viewCmd(services);
	}

	@Override
	public void execute() {		
		ArrayList<Service> services = model.getServiceSortedByPrice();
		this.viewGuestSortedByName(services);

	}

}
