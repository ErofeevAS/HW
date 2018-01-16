package com.erofeev.menu.parsercsv;

import java.util.ArrayDeque;
import java.util.ArrayList;

import com.erofeev.hotel.api.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;

public class ParserRoomsCSV {

	private final String separator = ";";

	public String[] listToCSV(ArrayList<Room> rooms) {

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
			str2.append(room.getPrice()).append(separator);
			str2.append(room.getCapacity()).append(separator);
			str2.append(room.getRoomStatus()).append(separator);
			str2.append(room.isEmpty()).append(separator);

			if (!roomHistory.isEmpty()) {
				str2.append("\"");
				for (Guest guest : roomHistory) {
					str2.append(guest.getID());
					str2.append(separator);
				}
				str2.append("\"");
			} else {
				str2.append("null");
			}

			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public ArrayList<Room> parseCSV(ArrayList<String> fileString, IReception reception) {

		ArrayList<Room> rooms = new ArrayList<Room>();
		ArrayDeque<Guest> roomHistory = new ArrayDeque<Guest>();
		for (int i = 1; i < fileString.size(); i++) {
			String[] parts = null;
			parts = fileString.get(i).split(separator);
			int ID = Integer.parseInt(parts[0]);
			String name = parts[1];
			int stars = Integer.parseInt(parts[2]);
			int capacity = Integer.parseInt(parts[3]);
			Float price = Float.parseFloat(parts[4]);
			RoomStatus status;
			if (parts[5].equals("SERVICED")) {
				status = RoomStatus.SERVICED;
			} else {
				status = RoomStatus.REPAIRABLE;
			}

			boolean empty;
			if (parts[6].equals("true")) {
				empty = true;
			} else {
				empty = false;
			}
			Room room = new Room(ID, name, stars, price, capacity, empty);
			room.setRoomStatus(status);
			String roomHistoryStr = parts[7];
			if (roomHistoryStr.equals("null")) {

			} else {
				String[] roomHistoryGuestsID = roomHistoryStr.split(separator);
				for (int j = 0; j < roomHistoryGuestsID.length; j++) {
					Guest guest = reception.findGuestbyID(Integer.parseInt(roomHistoryGuestsID[j]));
					roomHistory.add(guest);
				}
				room.setRoomHistory(roomHistory);
				Guest guestLast = roomHistory.getFirst();
				if (!room.isEmpty()) {
					guestLast.setRoom(room);
				}
			}
			rooms.add(room);
		}
		return rooms;
	}

}
