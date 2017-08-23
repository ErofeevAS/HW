package com.erofeev.hotel.sort;

import java.util.Comparator;
import com.erofeev.hotel.entity.*;

public class GuestSotredByName implements Comparator<Guest> {
	public int compare(Guest guest1, Guest guest2) {

		return (guest1.getFio()).compareTo(guest2.getFio());
	}
}
