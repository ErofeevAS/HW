package com.erofeev.menu.runner;



import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.reception.Reception;
import com.erofeev.menu.controller.MenuController;


public class ClientRunner {

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

		IReception model = new Reception();
		model.initFileManager(ROOMS_FILE, GUESTS_FILE, SERVICES_FILE);

		MenuController cntrl = new MenuController(model);

		try {
			cntrl.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
