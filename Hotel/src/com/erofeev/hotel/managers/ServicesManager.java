package com.erofeev.hotel.managers;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;

public class ServicesManager implements IManager {

	MyList<Service> services = new MyList<Service>();

	public boolean add(Service service) {
		boolean flag = false;
		for (int i = 0; i < services.length(); i++) {
			if ((services.get(i).equals(service))) {
				flag = true;
			}
		}
		if (!flag) {
			services.add(service);
		}
			
		return flag;
	}

	public void remove(Service service) {		
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
		String[] strServices = new String[currentServices.length()];
		StringBuilder str = new StringBuilder();		
		
		for (int i = 0; i < currentServices.length(); i++) {
			if (currentServices.get(i) != null) {
							
				strServices[i] = "";
				strServices[i] += currentServices.get(i).toString();

			}
		}
		return strServices;

	}

}
