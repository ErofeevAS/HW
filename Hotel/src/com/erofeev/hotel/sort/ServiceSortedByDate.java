package com.erofeev.hotel.sort;

import java.util.Comparator;
import com.erofeev.hotel.entity.*;

public class ServiceSortedByDate implements Comparator<Service> {
	public int compare(Service service1, Service service2) {

		return (service1.getDate()).compareTo(service2.getDate());
	}

}
