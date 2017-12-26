package com.erofeev.menu.runner;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.properties.Config;
import com.erofeev.hotel.reception.Reception;
import com.erofeev.menu.controller.MenuController;

public class ClientRunner {

	public static void main(String[] args) {

		IReception model = new Reception();

		model.initReception(Config.getConfig());

		MenuController cntrl = new MenuController(model);

		try {
			cntrl.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
