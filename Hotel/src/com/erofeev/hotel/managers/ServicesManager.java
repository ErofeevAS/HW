package com.erofeev.hotel.managers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Service;

public class ServicesManager extends AbstractManager<Service> {

	private static final Logger servicesManagerLogger = LogManager.getLogger(ServicesManager.class);

	ArrayList<Service> services = new ArrayList<Service>();

	public void add(Service service) {
		if (services.add(service)) {
			servicesManagerLogger.info(service.toString() + " was added.");
		} else {
			servicesManagerLogger.info(service.toString() + " already exists.");
		}
	}

	public void remove(Service service) {
		if (services.remove(service)) {
			servicesManagerLogger.info(service.toString() + " was remove.");
		} else {
			servicesManagerLogger.info(service.toString() + " can't find.");
		}

	}

	public Service findbyName(String name) {
		Service foundEntity = null;
		for (int i = 0; i < services.size(); i++) {
			if ((((IEntity) services.get(i)).getName()).equals(name)) {
				foundEntity = services.get(i);
				break;
			}
		}
		return foundEntity;
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
