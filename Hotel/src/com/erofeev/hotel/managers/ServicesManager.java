package com.erofeev.hotel.managers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Service;

public class ServicesManager extends AbstractManager<Service> {

	private static final Logger servicesManagerLogger = LogManager.getLogger(ServicesManager.class);
	private int currentId = 0;

	ArrayList<Service> services = new ArrayList<Service>();

	public void add(Service service) {
		service.setID(currentId);
		currentId++;
		if (services.add(service)) {
			servicesManagerLogger.info(service.toString() + " was added.");
		} else {
			currentId--;
			servicesManagerLogger.info(service.toString() + " already exists.");
		}
	}

	public void add(ArrayList<Service> newServices) {

		if (services.addAll(newServices)) {
			servicesManagerLogger.info(services.toString() + " was added.");
		} else {
			servicesManagerLogger.info(services.toString() + " already exists.");
		}

		currentId = this.getMaxId();

	}

	public void remove(Service service) {
		if (services.remove(service)) {
			servicesManagerLogger.info(service.toString() + " was remove.");
		} else {
			servicesManagerLogger.info(service.toString() + " can't find.");
		}

	}

	// public void update(ArrayList<Service> newServices) {
	// for (int i = 0; i < newServices.size(); i++) {
	// boolean containFlag = false;
	// Service newService = newServices.get(i);
	// int newID = newService.getID();
	// for (int j = 0; j < services.size(); j++) {
	// Service service = services.get(j);
	// int ID = service.getID();
	// if (ID == newID) {
	// services.remove(j);
	// services.add(j, newService);
	// containFlag = true;
	// break;
	// }
	// }
	// if (containFlag == false) {
	// services.add(newService);
	// }
	// }
	//
	// }

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

	public Service findbyID(int id) {
		Service foundEntity = null;
		for (int i = 0; i < services.size(); i++) {
			if ((((IEntity) services.get(i)).getID()) == (id)) {
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
