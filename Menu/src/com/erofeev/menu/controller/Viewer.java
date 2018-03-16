package com.erofeev.menu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.print.Printer;

public class Viewer {
	private static final Logger loggerViewer = LogManager.getLogger(Viewer.class);

	public static String readLine() throws IOException {
		String line = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		line = reader.readLine();
		return line;
	}

	public static String findEntity(String type) throws IOException {
		Printer.print("Enter " + type + " name:");
		String name = Viewer.readLine();
		return name;

	}

	public static String changePrice() throws IOException {
		Printer.print("Enter new Price: ");
		String price = Viewer.readLine();
		return price;

	}

	public static String changeStatus() throws IOException {
		Printer.print("Enter new Status: ");
		String status = Viewer.readLine();
		return status;

	}

	public static void viewCmd(List list) {
		for (int i = 0; i < list.size(); i++) {
			Printer.print(list.get(i));
		}
	}

	public static Room createRoom() throws IOException, IllegalArgumentException {

		String[] room = new String[4];
		Room newRoom = null;

		Printer.print("Enter Room name:");
		room[0] = Viewer.readLine();
		Printer.print("Enter Room stars:");
		room[1] = Viewer.readLine();
		Printer.print("Enter Room price:");
		room[2] = Viewer.readLine();
		Printer.print("Enter Room capacity:");
		room[3] = Viewer.readLine();

		String number = room[0];
		try {
			int stars = Integer.parseInt(room[1]);
			float price = Float.parseFloat(room[2]);
			int capacity = Integer.parseInt(room[3]);
			newRoom = new Room(number, stars, price, capacity);
		} catch (NumberFormatException e) {
			loggerViewer.error("Wrong input data " + e);
			throw new IllegalArgumentException();
		}

		return newRoom;
	}

	public static Service createService() throws IOException, IllegalArgumentException {
		String[] service = new String[2];
		Service newService = null;
		Printer.print("Enter Service name:");
		service[0] = Viewer.readLine();
		Printer.print("Enter Service price:");
		service[1] = Viewer.readLine();
		try {
			newService = new Service(service[0], Float.parseFloat(service[1]));
		} catch (NumberFormatException e) {
			loggerViewer.error("Wrong input data " + e);
			throw new IllegalArgumentException();
		}
		return newService;
	}

	public static Guest createGuest() throws IOException, IllegalArgumentException {
		Guest guest = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String[] strGuest = new String[4];

		Printer.print("Enter Guest firstName:");
		strGuest[0] = Viewer.readLine();
		Printer.print("Enter Guest secondName:");
		strGuest[1] = Viewer.readLine();
		Printer.print("Enter Guest arrival Date:");
		strGuest[2] = Viewer.readLine();
		Printer.print("Enter Guest leaving Date:");
		strGuest[3] = Viewer.readLine();

		String firstName = strGuest[0];
		String secondName = strGuest[1];
		try {
			Date arrivalDate = dateFormat.parse(strGuest[2]);
			Date leavingDate = dateFormat.parse(strGuest[3]);
			guest = new Guest(firstName, secondName, arrivalDate, leavingDate);
		} catch (ParseException e) {
			loggerViewer.error("Wrong Date format " + e);
			throw new IllegalArgumentException();
		}
		return guest;

	}

	public static List<String> modifyRoom() throws IOException {
		List<String> newParameters = new ArrayList<String>();
		Printer.print("If you dont need change current parameter just enter: null");
		Printer.print("Enter new Room Number:");
		newParameters.add(Viewer.readLine());
		Printer.print("Enter new Room Stars:");
		newParameters.add(Viewer.readLine());
		Printer.print("Enter new  Room Price:");
		newParameters.add(Viewer.readLine());
		Printer.print("Enter new Room Capacity:");
		newParameters.add(Viewer.readLine());

		return newParameters;
	}

	public static String getFileName() throws IOException {
		Printer.print("Enter file name:");
		return Viewer.readLine();
	}

}
