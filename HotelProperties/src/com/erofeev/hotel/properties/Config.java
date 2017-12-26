package com.erofeev.hotel.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class Config {

	public static ArrayList<String> getConfig() {
		String GUESTS_FILE;
		String ROOMS_FILE;
		String SERVICES_FILE;
		String changeStatusFlag;
		String roomHistorySize;
		Properties properties = new Properties();
		ArrayList<String> parameters = new ArrayList<String>();

		try (FileInputStream in = new FileInputStream("ui.properties")) {

			properties.load(in);
			changeStatusFlag = properties.getProperty("changeStatusFlag");
			roomHistorySize = properties.getProperty("roomHistorySize");
			ROOMS_FILE = properties.getProperty("rooms");
			GUESTS_FILE = properties.getProperty("guests");
			SERVICES_FILE = properties.getProperty("services");

			if (changeStatusFlag.equals(null)) {
				changeStatusFlag = "false";
			}

			if (roomHistorySize.equals(null)) {
				roomHistorySize = "10";
			}

			if (GUESTS_FILE.equals(null)) {
				GUESTS_FILE = "guests.txt";
			}
			if (ROOMS_FILE.equals(null)) {
				ROOMS_FILE = "rooms.txt";
			}

			if (SERVICES_FILE.equals(null)) {
				SERVICES_FILE = "services.txt";
			}

			parameters.add(changeStatusFlag);
			parameters.add(roomHistorySize);
			parameters.add(ROOMS_FILE);
			parameters.add(GUESTS_FILE);
			parameters.add(SERVICES_FILE);

		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace());

		} catch (IOException e) {
			e.getMessage();

		}
		return parameters;
	}

}
