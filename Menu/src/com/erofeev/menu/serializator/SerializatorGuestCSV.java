
package com.erofeev.menu.serializator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;

public class SerializatorGuestCSV {

	public String[] serialize(ArrayList<Guest> guests) {

		String separator = ";";
		int cnt = 1;
		String[] serialStr = new String[guests.size() + 1];
		StringBuilder str = new StringBuilder();
		str.append("GuestId").append(separator);
		str.append("FirstName").append(separator);
		str.append("SurName").append(separator);
		str.append("Room Number").append(separator);
		str.append("ArrivalDate").append(separator);
		str.append("LeavingDate").append(separator);
		str.append("Services").append(separator);

		serialStr[0] = str.toString();

		for (Guest guest : guests) {
			StringBuilder str2 = new StringBuilder();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

			ArrayList<Service> services = guest.getGuestServices();

			str2.append(guest.getID()).append(separator);
			str2.append(guest.getFirstName()).append(separator);
			str2.append(guest.getSurName()).append(separator);
			str2.append(guest.getRoom().getName()).append(separator);
			str2.append(dateFormat.format(guest.getArrivalDate())).append(separator);
			str2.append(dateFormat.format(guest.getLeavingDate())).append(separator);
			str2.append("\"");
			for (Service service : services) {

				str2.append(service);

				// str2.append(service.getID()).append(separator);
				// str2.append(service.getName()).append(separator);
				// str2.append(service.getPrice());
				// str.append(System.getProperty("line.separator"));

			}
			// str2.deleteCharAt(str2.length() - 1);
			str2.append("\"");

			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public ArrayList<Guest> desrialize(String[] lines) {

		return null;
	}

}
