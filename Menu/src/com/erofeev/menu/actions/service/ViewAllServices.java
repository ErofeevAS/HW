package com.erofeev.menu.actions.service;

import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;

public class ViewAllServices implements IAction {
	private IReception model;

	public ViewAllServices(IReception model) {
		super();
		this.model = model;
	}

	@Override
	public void execute() {
		ArrayList<Service> services = model.getAllServices();
		for (Service service : services) {
			Printer.print(service);
		}
	}

}
