package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;

public class ServicesManager implements IManager {

	MyList<Service> services = new MyList<Service>();

	public void add(Service service) {
		services.add(service);
		System.out.println("Service: " + service + " was added");
	}

	public void remove(Service service) {
		System.out.println("Service: " + service + " was removed");
		services.remove(service);

	}

	public MyList<Service> getServices() {
		return services;

	}

	public float getPrice() {
		float price = 0;
		for (int i = 0; i < services.length(); i++) {
			price = +services.get(i).getPrice();
		}
		return price;
	}

	public void changeServicePrice(Service service, float price) {
		(services.get(services.find(service))).setPrice(price);
	}

	@Override
	public String[] read() {

		MyList<Service> currentServices = this.getServices();
		String[] strServices = new String[currentServices.length() + 1];
		StringBuilder str = new StringBuilder();
		str.append("Services:");
		strServices[0] = "";
		strServices[0] += str.toString();
		
		for (int i = 0; i < currentServices.length(); i++) {
			if (currentServices.get(i) != null) {
				str.delete(0, str.capacity());
				str.append(currentServices.get(i));
				strServices[i + 1] = "";
				strServices[i + 1] += str.toString();

			}
		}
		return strServices;

	}

}
