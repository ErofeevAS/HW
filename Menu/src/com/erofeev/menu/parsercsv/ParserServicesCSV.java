package com.erofeev.menu.parsercsv;

import java.util.ArrayList;

import com.erofeev.hotel.entity.Service;

public class ParserServicesCSV {

	private final String separator = ";";

	public String[] listToCSV(ArrayList<Service> services) {

		String separator = ";";
		int cnt = 1;
		String[] serialStr = new String[services.size() + 1];
		StringBuilder str = new StringBuilder();
		str.append("ServiceId").append(separator).append("Name").append(separator).append("Price");
		serialStr[0] = str.toString();
		for (Service service : services) {
			StringBuilder str2 = new StringBuilder();
			str2.append(service.getID()).append(separator).append(service.getName()).append(separator)
					.append(service.getPrice());
			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public ArrayList<Service> parseCSV(ArrayList<String> fileString) throws NumberFormatException {
		ArrayList<Service> services = new ArrayList<Service>();
		for (int i = 1; i < fileString.size(); i++) {
			String[] parts = null;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			String name = parts[1];
			Float price = Float.parseFloat(parts[2]);
			Service service = new Service(ID, name, price);
			services.add(service);
		}
		return services;
	}

}
