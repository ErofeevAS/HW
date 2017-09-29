package com.erofeev.hotel.managers;

import com.erofeev.hotel.entity.Service;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.mylist.MyList;

public class GuestManager implements IManager {

	private MyList<Guest> guests = new MyList<Guest>();
	private MyList<Guest> guestsHistory = new MyList<Guest>();
	final long MILISEC_IN_DAY = 86400000;

	public void add(Guest guest) {
		guests.add(guest);		
	}

	public void remove(Guest guest) {		
		guests.remove(guest);
		guestsHistory.add(guest);
	}

	public MyList<Guest> getGuestsHistory() {
		return guestsHistory;
	}

	public MyList<Guest> getAllGuest() {
		return guests;
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

	@Override
	public String[] read() {
		MyList<Guest> currentGuests = this.getAllGuest();
		MyList<Service> services;
		String[] strGuests = new String[currentGuests.length()];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);	
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < currentGuests.length(); i++) {
			if (currentGuests.get(i) != null) {
				str.delete(0, str.capacity());
				str.append(currentGuests.get(i).getFirstName());
				str.append(" ");
				str.append(currentGuests.get(i).getSecondName());
				str.append(" ");
				str.append(dateFormat.format(currentGuests.get(i).getArrivalDate()));
				str.append(" ");
				str.append(dateFormat.format(currentGuests.get(i).getLeavingDate()));				
				str.append(" ");				
				str.append(currentGuests.get(i).getRoom().getName());
			
				StringBuilder strService = new StringBuilder();

				services = currentGuests.get(i).getGuestServices();
				for (int j = 0; j < services.length(); j++) {					
					strService.append(" ");		
					strService.append(services.get(j).getName());
								

				}
				str.append(strService);
				strGuests[i] = "";
				strGuests[i] += str.toString();
			}
		}
		return strGuests;
	}

}
