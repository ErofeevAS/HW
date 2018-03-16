package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Room;

public class RoomsSortedByPrice implements Comparator<Room> {
	public int compare(Room room1, Room room2) {
		float price1 = room1.getPrice();
		float price2 = room2.getPrice();
		if (price1 > price2) {
			return 1;
		} else if (price1 < price2) {
			return -1;
		} else {
			return 0;
		}
	}

}
