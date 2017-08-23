package com.erofeev.hotel.manager;

import com.erofeev.hotel.entity.*;

public class RoomManager {

	private Guest guest;
	private Room room;
	private ServiceList serviceList;
	private Guest[] roomHistory = new Guest[3];

	public RoomManager() {

	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
		room.setEmpty(false);
		roomHistory[2] = roomHistory[1];
		roomHistory[1] = roomHistory[0];
		roomHistory[0] = guest;
	}

	public void removeGuest(Guest guest) {
		this.guest = null;
		room.setEmpty(true);
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;

	}

	public void removeRoom(Room room) {
		this.room = null;
	}

	public ServiceList getServiceList() {
		return serviceList;
	}

	public void setServiceList(ServiceList servicelist) {
		this.serviceList = servicelist;
	}

	public Guest[] getRoomHistory() {
		return roomHistory;
	}

}
