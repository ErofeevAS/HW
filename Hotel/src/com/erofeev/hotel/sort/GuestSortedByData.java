package com.erofeev.hotel.sort;

import java.util.Comparator;
import com.erofeev.hotel.entity.*;

public class GuestSortedByData implements Comparator<Guest> {
	public int compare(Guest guest1, Guest guest2) {

		return (guest1.getOutDate()).compareTo(guest2.getOutDate());
	}
}
