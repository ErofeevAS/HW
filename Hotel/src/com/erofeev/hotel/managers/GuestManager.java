package com.erofeev.hotel.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.entity.IEntity;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;

public class GuestManager extends AbstractManager<Guest> {

	private static final Logger loggerGuestManager = LogManager.getLogger(GuestManager.class);
	final long MILISEC_IN_DAY = 86400000;

	private List<Guest> guests = new ArrayList<Guest>();
	private int currentId = 0;

	public void add(Guest guest) {
		currentId++;
		guest.setId(currentId);		
		if (guests.add(guest)) {
			loggerGuestManager.info(guest.toString() + " was added.");
		} else {
			loggerGuestManager.info(guest.toString() + " already exists.");
			currentId--;
		}
	}

	public void add(List<Guest> newGuests) {
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

	public Guest findbyId(int id) {
		Guest foundEntity = null;
		for (int i = 0; i < guests.size(); i++) {
			if ((((IEntity) guests.get(i)).getId()) == (id)) {
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
		List<Service> guestServices = guest.getGuestServices();
		for (int i = 0; i < guestServices.size(); i++) {
			servicesPrice += guestServices.get(i).getPrice();
		}
		return servicesPrice * Date;
	}

	public List<Guest> getAll() {
		return guests;
	}

}
