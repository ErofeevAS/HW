package com.erofeev.hotel.managers;

import com.erofeev.hotel.entity.Service;
import com.erofeev.hotel.api.IManager;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.mylist.MyList;

public class GuestManager implements IManager {

	private MyList<Guest> guests = new MyList<Guest>();
	private MyList<Guest> guestsHistory = new MyList<Guest>();
	final long MILISEC_IN_DAY = 86400000;

	public void add(Guest guest) {
		guests.add(guest);
		System.out.println(guest + " was added");
	}

	public void remove(Guest guest) {
		System.out.println(guest + " was removed");
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
		String[] strGuests = new String[currentGuests.length() + 1];
		StringBuilder str = new StringBuilder();
		str.append("");
		str.append("Guests:");
		strGuests[0] = "";
		strGuests[0] += str.toString();

		for (int i = 0; i < currentGuests.length(); i++) {
			if (currentGuests.get(i) != null) {
				str.delete(0, str.capacity());
				str.append(currentGuests.get(i));
				str.append(" room #");
				str.append(currentGuests.get(i).getRoom().getName());
				strGuests[i + 1] = "";
				strGuests[i + 1] += str.toString();
			}
		}
		return strGuests;
	}

}
