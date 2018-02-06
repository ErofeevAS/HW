
package com.erofeev.menu.parsercsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.Service;

public class ParserGuestsCSV {
	private final String separator = ";";
	private final String nestedSeparator = " ";

	public String[] listToCSV(ArrayList<Guest> guests) {

		int cnt = 1;
		String[] serialStr = new String[guests.size() + 1];
		StringBuilder str = new StringBuilder();
		str.append("GuestId").append(separator);
		str.append("FirstName").append(separator);
		str.append("SurName").append(separator);
		str.append("ArrivalDate").append(separator);
		str.append("LeavingDate").append(separator);
		str.append("Room ID").append(separator);
		str.append("Services ID").append(separator);

		serialStr[0] = str.toString();

		for (Guest guest : guests) {
			StringBuilder str2 = new StringBuilder();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

			ArrayList<Service> services = guest.getGuestServices();

			str2.append(guest.getID()).append(separator);
			str2.append(guest.getFirstName()).append(separator);
			str2.append(guest.getSurName()).append(separator);
			str2.append(dateFormat.format(guest.getArrivalDate())).append(separator);
			str2.append(dateFormat.format(guest.getLeavingDate())).append(separator);
			if (guest.getRoom() == null) {
				str2.append("null").append(separator);
			} else {
				str2.append(guest.getRoom().getID()).append(separator);
			}

			if (guest.getGuestServices().size() == 0) {
				str2.append("null").append(separator);
			} else {
				// str2.append("\"");
				for (Service service : services) {
					str2.append(service.getID());
					str2.append(nestedSeparator);
				}
				// str2.append("\"");
			}

			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public ArrayList<Guest> parseCSV(ArrayList<String> fileString, IReception reception) throws NumberFormatException {

		ArrayList<Guest> guests = new ArrayList<Guest>();
		ArrayList<Service> services = new ArrayList<Service>();
		Room room;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		for (int i = 1; i < fileString.size(); i++) {
			String[] parts = null;
			Guest guest;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			String firstName = parts[1];
			String surName = parts[2];
			String roomID = parts[5];

			try {
				Date arrivalDate = dateFormat.parse(parts[3]);
				Date leavingDate = dateFormat.parse(parts[4]);
				guest = new Guest(firstName, surName, arrivalDate, leavingDate);
				guest.setID(ID);
			} catch (ParseException e) {
				// loggerViewer.error("Wrong Date format " + e);
				throw new IllegalArgumentException();
			}
			if (roomID.equals("null")) {

			} else {
				room = reception.findRoombyID(Integer.parseInt(roomID));
				if (room != null) {
					room.setEmpty(false);
					guest.setRoom(room);
				}
			}

			if (parts[6].equals("null")) {

			} else {
				String[] servicesID = parts[6].split(nestedSeparator);
				for (int j = 0; j < servicesID.length; j++) {
					Service service = reception.findServicebyID(Integer.parseInt(servicesID[j]));
					services.add(service);
				}
				guest.setGuestServices(services);
			}
			guests.add(guest);

		}
		return guests;

	}
}
