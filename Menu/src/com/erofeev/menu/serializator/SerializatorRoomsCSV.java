package com.erofeev.menu.serializator;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;

public class SerializatorRoomsCSV {

	public String[] serialize(ArrayList<Room> rooms) {

		String separator = ";";
		int cnt = 1;
		String[] serialStr = new String[rooms.size() + 1];

		StringBuilder str = new StringBuilder();
		str.append("RoomId").append(separator);
		str.append("Name").append(separator);
		str.append("Stars").append(separator);
		str.append("Capacity").append(separator);
		str.append("Price").append(separator);
		str.append("Status").append(separator);
		str.append("Empy").append(separator);
		str.append("RoomHistory").append(separator);

		serialStr[0] = str.toString();

		for (Room room : rooms) {
			StringBuilder str2 = new StringBuilder();
			ArrayDeque<Guest> roomHistory = room.getRoomHistory();

			str2.append(room.getID()).append(separator);
			str2.append(room.getName()).append(separator);
			str2.append(room.getStars()).append(separator);
			str2.append(room.getCapacity()).append(separator);
			str2.append(room.getPrice()).append(separator);
			str2.append(room.getRoomStatus()).append(separator);
			str2.append(room.isEmpty()).append(separator);

			str2.append("\"");
			for (Guest guest : roomHistory) {
				str2.append(guest);
			}
			str2.append("\"");

			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public ArrayList<Room> desrialize(String[] lines) {

		return null;
	}

}
