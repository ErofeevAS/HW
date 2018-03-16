package com.erofeev.menu.parsercsv;

import java.util.ArrayList;
import java.util.List;

import com.erofeev.hotel.entity.Service;

public class ParserServicesCSV {

	private final String separator = ";";

	public String[] listToCSV(List<Service> services) {

		String separator = ";";
		int cnt = 1;
		String[] serialStr = new String[services.size() + 1];
		StringBuilder str = new StringBuilder();
		str.append("ServiceId").append(separator).append("Name").append(separator).append("Price");
		serialStr[0] = str.toString();
		for (Service service : services) {
			StringBuilder str2 = new StringBuilder();
			str2.append(service.getId()).append(separator).append(service.getName()).append(separator)
					.append(service.getPrice());
			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public List<Service> parseCSV(List<String> fileString) throws NumberFormatException {
		List<Service> services = new ArrayList<Service>();
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
