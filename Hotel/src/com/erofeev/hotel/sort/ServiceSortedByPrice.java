package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Service;

public class ServiceSortedByPrice implements Comparator<Service> {
	public int compare(Service service1, Service service2) {

		float price1 = service1.getPrice();
		float price2 = service2.getPrice();

		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}

}
