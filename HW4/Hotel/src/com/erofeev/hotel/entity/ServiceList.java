package com.erofeev.hotel.entity;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ServiceList {
	private int serviceCounter = 0;
	private Service[] serviceList = new Service[10];

	public void incServiceCounter() {
		serviceCounter++;
	}

	public void dicreaseServiceCounter() {
		serviceCounter--;
	}

	public void setServiceCounter(int index) {
		this.serviceCounter = index;
	}

	public int getServiceCounter() {
		return serviceCounter;
	}

	public void addService(Service service) {
		// System.out.println(getServiceCounter());
		serviceList[getServiceCounter()] = service;
		incServiceCounter();

	}

	public int getNotNull() {
		int result = 0;
		for (int i = 0; i < serviceList.length; i++) {
			if ((serviceList[i]) != null) {
				result++;
			}
		}
		return result;
	}

	public Service[] getServiceList() {
		return serviceList;
	}

	public float getAllPrice() {
		float totalServicePrice = 0;
		for (int i = 0; i < serviceList.length; i++) {
			if (serviceList[i] != null) {
				totalServicePrice += serviceList[i].getPrice();
			}

		}
		return totalServicePrice;
	}

	public void viewServiceList() {
		for (int i = 0; i < serviceList.length; i++) {
			if ((serviceList[i]) != null) {
				System.out.println(serviceList[i]);
			}
		}
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Service[] servies = getServiceList();

		for (int i = 0; i < servies.length; i++) {
			if (servies[i] != null) {
				str.append(servies[i].getName());
				str.append(dateFormat.format(servies[i].getDate()));
				str.append(servies[i].getPrice());
			}
		}
		return str.toString();
	}

}
