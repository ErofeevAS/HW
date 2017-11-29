package com.erofeev.hotel.managers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.entity.Service;


public class ServicesManager extends AbstractManager<Service> {

	private static final Logger servicesManagerLogger = LogManager.getLogger(ServicesManager.class);

	ArrayList<Service> services = getEntities();

	public boolean add(Service service) {
		if (super.add(service)) {			
			return true;
		} else {			
			servicesManagerLogger.info(service.toString()  + " was added.");
			return false;
		}
	}

	public void remove(Service service) {
		services.remove(service);
		servicesManagerLogger.info(service.getName() + " was removed.");
	}

	public ArrayList<Service> getAll() {
		return services;
	}
	public void changeServicePrice(Service service, float price) {

		int indexFoundService = services.indexOf(service);
		if (indexFoundService != -1) {
			services.get(indexFoundService).setPrice(price);
			servicesManagerLogger.info(service.getName() + " price was changed.");
		} else {
			servicesManagerLogger.warn(service.getName() + " can't find.");

		}

	}

	

}
