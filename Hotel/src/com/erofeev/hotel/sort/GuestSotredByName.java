package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Guest;

public class GuestSotredByName implements Comparator<Guest> {
	public int compare(Guest guest1, Guest guest2) {
		String nameGuest1 =  guest1.getSurName() + " " + guest1.getFirstName();
		String nameGuest2 =  guest2.getSurName() + " " + guest2.getFirstName();

		return (nameGuest1).compareTo(nameGuest2);
	}
}
