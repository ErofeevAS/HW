package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Guest;

public class GuestSortedByDate implements Comparator<Guest> {
	public int compare(Guest guest1, Guest guest2) {
		return (guest1.getLeavingDate()).compareTo(guest2.getLeavingDate());
	}
}
