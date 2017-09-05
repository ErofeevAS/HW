package com.erofeev.hotel.entity;

import java.util.*;
import java.util.Locale;
import java.text.SimpleDateFormat;

import com.erofeev.hotel.mylist.MyList;;

public class Guest {
	private String firstName;
	private String secondName;	
	private Date arrivalDate;
	private Date leavingDate;
	private Room room;
	private MyList<Service> services;

	public Guest(String firstName, String secondName, Date arrivalDate, Date leavingDate) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.arrivalDate = arrivalDate;
		this.leavingDate = leavingDate;
		this.services = new MyList<Service>();		
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public MyList<Service> getGuestServices() {
		return services;
	}

	public void addGuestService(Service service) {
		services.add(service);
	}

	public void removeGuestService(Service service) {
		services.remove(service);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}	

	public Date getLeavingDate() {
		return leavingDate;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		str.append("Guest name: ").append(this.getFirstName()).append(" ").append(this.getSecondName())
				.append(". Arrival Date: ").append(dateFormat.format(getArrivalDate())).append(". Leaving Date: ")
				.append(dateFormat.format(getLeavingDate()));

		return str.toString();
	}

}
