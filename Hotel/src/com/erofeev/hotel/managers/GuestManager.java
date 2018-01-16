package com.erofeev.hotel.managers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;

public class GuestManager extends AbstractManager<Guest> {

	private ArrayList<Guest> guests = new ArrayList<Guest>();
	private static final Logger loggerGuestManager = LogManager.getLogger(GuestManager.class);
	private int currentId = 0;

	final long MILISEC_IN_DAY = 86400000;

	public void add(Guest guest) {
		guest.setID(currentId);
		currentId++;
		if (guests.add(guest)) {
			loggerGuestManager.info(guest.toString() + " was added.");
		} else {
			loggerGuestManager.info(guest.toString() + " already exists.");
			currentId--;
		}
	}

	public void add(ArrayList<Guest> newGuests) {
		if (guests.addAll(newGuests)) {
			loggerGuestManager.info(newGuests.toString() + " was added.");
		} else {
			loggerGuestManager.info(newGuests.toString() + " already exists.");
		}
		currentId = this.getMaxId();
	}

	public void remove(Guest guest) {
		guests.remove(guest);
		loggerGuestManager.info(guest.toString() + " was removed.");
	}

	public void evict(Guest guest) {
		guest.getRoom().setEmpty(true);
		loggerGuestManager.info(guest.toString() + " was evicted.");
	}

	public Guest findbyName(String name) {
		Guest foundEntity = null;
		for (int i = 0; i < guests.size(); i++) {
			if ((((IEntity) guests.get(i)).getName()).equals(name)) {
				foundEntity = guests.get(i);
				break;
			}
		}

		return foundEntity;
	}

	public Guest findbyID(int id) {
		Guest foundEntity = null;
		for (int i = 0; i < guests.size(); i++) {
			if ((((IEntity) guests.get(i)).getID()) == (id)) {
				foundEntity = guests.get(i);
				break;
			}
		}
		return foundEntity;

	}

	public long getGuestOccupyDays(Guest guest) {
		long numberDays = (guest.getLeavingDate().getTime() - guest.getArrivalDate().getTime()) / MILISEC_IN_DAY;
		return numberDays;
	}

	public float getGuestRoomPrice(Guest guest) {

		long Date = getGuestOccupyDays(guest);
		float roomPrice = guest.getRoom().getPrice();
		return roomPrice * Date;
	}

	public float getGuestServicesPrice(Guest guest) {

		float servicesPrice = 0;
		long Date = getGuestOccupyDays(guest);

		ArrayList<Service> guestServices = guest.getGuestServices();
		for (int i = 0; i < guestServices.size(); i++) {
			servicesPrice += guestServices.get(i).getPrice();
		}
		return servicesPrice * Date;
	}

	public ArrayList<Guest> getAll() {
		return guests;
	}

}
