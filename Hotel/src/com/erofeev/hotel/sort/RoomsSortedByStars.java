package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Room;

public class RoomsSortedByStars implements Comparator<Room> {
	public int compare(Room room1, Room room2) {

		int stars1 = room1.getStars();
		int stars2 = room2.getStars();

		if (stars1 > stars2) {
			return 1;
		} else if (stars1 < stars2) {
			return -1;
		} else {
			return 0;
		}
	}
}
