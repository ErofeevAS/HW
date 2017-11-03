package com.erofeev.hotel.sort;

import java.util.Comparator;

import com.erofeev.hotel.entity.Room;

public class RoomsSortedByCapacity implements Comparator<Room> {

	public int compare(Room room1, Room room2) {

		int capacity1 = room1.getCapacity();
		int capacity2 = room2.getCapacity();

		if (capacity1 > capacity2) {
			return 1;
		} else if (capacity1 < capacity2) {
			return -1;
		} else {
			return 0;
		}
	}
}
