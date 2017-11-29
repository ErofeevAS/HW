package com.erofeev.hotel.managers;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;

public class GuestManager extends AbstractManager<Guest> {

	private ArrayList<Guest> guests = getEntities();
	private ArrayList<Guest> guestsHistory = new ArrayList<Guest>();
	private static final Logger loggerGuestManager = LogManager.getLogger(GuestManager.class);
	
	final long MILISEC_IN_DAY = 86400000;

	public boolean add(Guest guest) {
		if (super.add(guest)) {			
			return true;
		} else {
			guestsHistory.add(guest);
			loggerGuestManager.info(guest.toString()  + " was added.");
			return false;
		}
	}

	public ArrayList<Guest> getGuestsHistory() {
		return guestsHistory;
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
	

	

}
