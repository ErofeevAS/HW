package com.erofeev.hotel.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.mylist.MyList;

public class GuestManager extends AbstractManager<Guest> {

	private MyList<Guest> guests = getEntities();
	private MyList<Guest> guestsHistory = new MyList<Guest>();
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

	public MyList<Guest> getGuestsHistory() {
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

		MyList<Service> guestServices = guest.getGuestServices();
		for (int i = 0; i < guestServices.length(); i++) {
			servicesPrice += guestServices.get(i).getPrice();
		}		
		return servicesPrice * Date;
	}
	

	

}
