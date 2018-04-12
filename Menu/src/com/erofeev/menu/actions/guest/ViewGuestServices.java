package com.erofeev.menu.actions.guest;

import java.io.IOException;
import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;
import com.erofeev.menu.api.IAction;
import com.erofeev.menu.controller.Viewer;

public class ViewGuestServices implements IAction {
	private IReception model;

	public ViewGuestServices(IReception model) {
		this.model = model;
	}

	@Override
	public void execute() throws IOException {
		String name = Viewer.findEntity("Guest");
		List<Service> services = model.findGuestbyName(name).getGuestServices();
		Printer.print("Guest services:");
		for (Service service : services) {
			Printer.print(service);
		}
	}
}