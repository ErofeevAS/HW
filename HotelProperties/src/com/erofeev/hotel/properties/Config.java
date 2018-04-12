package com.erofeev.hotel.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {

	private static final Logger loggerConfig = LogManager.getLogger(Config.class);
	
	static String GUESTS_FILE;
	static String ROOMS_FILE;
	static String SERVICES_FILE;
	static String changeStatusFlag;
	static String roomHistorySize;
	static String path;
	static String IReception;
	static String INavigator;
	static String IBuilder;
	static String IMenuController;

	public static List<String> getConfig() {
		

		Properties properties = new Properties();
		List<String> parameters = new ArrayList<String>();

		try (FileInputStream in = new FileInputStream("ui.properties")) {

			properties.load(in);
			changeStatusFlag = properties.getProperty("changeStatusFlag");
			roomHistorySize = properties.getProperty("roomHistorySize");
			ROOMS_FILE = properties.getProperty("rooms");
			GUESTS_FILE = properties.getProperty("guests");
			SERVICES_FILE = properties.getProperty("services");
			path = properties.getProperty("PATH");
			IReception = properties.getProperty("IDAO");
			INavigator = properties.getProperty("navigator");
			IBuilder = properties.getProperty("builder");
			IMenuController = properties.getProperty("menucontroller");

			if (changeStatusFlag == null) {
				changeStatusFlag = "false";
			}

			if (roomHistorySize == null) {
				roomHistorySize = "10";
			}

			if (GUESTS_FILE == null) {
				GUESTS_FILE = "guests.txt";
			}
			if (ROOMS_FILE == null) {
				ROOMS_FILE = "rooms.txt";
			}

			if (SERVICES_FILE == null) {
				SERVICES_FILE = "services.txt";
			}

			if (path == null) {
				path = "/TEMP/";
			}
			if (IReception == null) {
				throw new RuntimeException();
			}

			if (INavigator == null) {
				throw new RuntimeException();
			}
			if (IBuilder == null) {
				throw new RuntimeException();
			}
			if (IMenuController == null) {
				throw new RuntimeException();
			}

			parameters.add(changeStatusFlag);
			parameters.add(roomHistorySize);
			parameters.add(ROOMS_FILE);
			parameters.add(GUESTS_FILE);
			parameters.add(SERVICES_FILE);
			parameters.add(path);
			parameters.add(IReception);
			parameters.add(INavigator);
			parameters.add(IBuilder);
			parameters.add(IMenuController);

		} catch (FileNotFoundException e) {
			loggerConfig.warn("Properties file not found");

		} catch (IOException e) {
			loggerConfig.warn("Can't read properties file");

		} catch (RuntimeException e) {
			loggerConfig.error("Can't find reflection class name in the properties file");
		}
		return parameters;
	}
	
	public static String getServicesPath(){
		return path+"services.csv";
	}

}
