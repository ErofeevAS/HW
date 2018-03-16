package com.erofeev.menu.parsercsv;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import com.erofeev.hotel.api.reception.IReception;
import com.erofeev.hotel.entity.Guest;
import com.erofeev.hotel.entity.Room;
import com.erofeev.hotel.entity.RoomStatus;

public class ParserRoomsCSV {

	private final String separator = ";";
	private final String nestedSeparator = " ";

	public String[] listToCSV(List<Room> rooms) {

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
			List<Guest> roomHistory = room.getRoomHistory();

			str2.append(room.getId()).append(separator);
			str2.append(room.getName()).append(separator);
			str2.append(room.getStars()).append(separator);
			str2.append(room.getCapacity()).append(separator);
			str2.append(room.getPrice()).append(separator);
			str2.append(room.getRoomStatus()).append(separator);
			str2.append(room.isEmpty()).append(separator);

			if (!roomHistory.isEmpty()) {
				for (Guest guest : roomHistory) {
					str2.append(guest.getId());
					str2.append(nestedSeparator);
				}
			} else {
				str2.append("null");
			}

			serialStr[cnt] = "" + str2;
			cnt++;

		}

		return serialStr;

	}

	public List<Room> parseCSV(List<String> fileString, IReception reception) throws NumberFormatException {

		List<Room> rooms = new ArrayList<Room>();
		List<Guest> roomHistory = new ArrayList<Guest>();
		for (int i = 1; i < fileString.size(); i++) {
			roomHistory.clear();
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
				String[] roomHistoryGuestsID = roomHistoryStr.split(nestedSeparator);
				for (int j = 0; j < roomHistoryGuestsID.length; j++) {
					Guest guest = reception.findGuestbyID(Integer.parseInt(roomHistoryGuestsID[j]));
					roomHistory.add(guest);
				}
				room.setRoomHistory(roomHistory);
				Guest guestLast = roomHistory.get(i-1);
				if (!room.isEmpty()) {
					guestLast.setRoom(room);
				}
			}
			rooms.add(room);
		}
		return rooms;
	}

}
