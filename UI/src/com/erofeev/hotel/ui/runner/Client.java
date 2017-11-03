package com.erofeev.hotel.ui.runner;

import com.erofeev.hotel.reception.Reception;
import com.erofeev.hotel.ui.api.AbstractController;
import com.erofeev.hotel.ui.api.AbstractUI;
import com.erofeev.hotel.ui.api.AbstractView;

import com.erofeev.hotel.ui.cmd.CmdUI;
import com.erofeev.hotel.ui.cmd.WebUI;

public class Client {

	public static void main(String[] args) {

		String GUESTS_FILE;
		String ROOMS_FILE;
		String SERVICES_FILE;
		if (args.length == 0) {

			GUESTS_FILE = "guests.txt";
			ROOMS_FILE = "rooms.txt";
			SERVICES_FILE = "services.txt";

		} else {

			GUESTS_FILE = args[0];
			ROOMS_FILE = args[1];
			SERVICES_FILE = args[2];

		}

		Reception model = new Reception();
		model.initFileManager(ROOMS_FILE, GUESTS_FILE, SERVICES_FILE);

		AbstractUI factory = getUIByName("CMD");
		AbstractView view = factory.createView();
		AbstractController controller = factory.createController();
		controller.setModel(model);
		controller.setView(view);
		controller.startController();

	}

	private static AbstractUI getUIByName(String name) {

		switch (name) {
		case "CMD":
			return CmdUI.getInstance();
		case "WEB":
			return new WebUI();
		default:
			throw new RuntimeException("Unsupported UI: " + name);
		}

	}
}
