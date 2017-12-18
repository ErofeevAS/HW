package com.erofeev.hotel.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Conf {

	public void getConf() {
		String GUESTS_FILE;
		String ROOMS_FILE;
		String SERVICES_FILE;
		String status;
		String roomNote;
		Properties properties = new Properties();

		try (FileInputStream in = new FileInputStream("ui.properties")) {

			properties.load(in);
			GUESTS_FILE = properties.getProperty("guests");
			ROOMS_FILE = properties.getProperty("rooms");
			SERVICES_FILE = properties.getProperty("services");
			status = properties.getProperty("status");
			roomNote = properties.getProperty("roomNote");

			if (GUESTS_FILE.equals(null)) {
				GUESTS_FILE = "guests.txt";
			}
			if (ROOMS_FILE.equals(null)) {
				ROOMS_FILE = "rooms.txt";
			}

			if (SERVICES_FILE.equals(null)) {
				SERVICES_FILE = "services.txt";
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace());

		} catch (IOException e) {
			e.getMessage();

		}
	}

}
