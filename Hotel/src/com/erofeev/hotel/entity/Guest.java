package com.erofeev.hotel.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.erofeev.hotel.api.IEntity;

public class Guest implements IEntity,Serializable {
	private String firstName;
	private String secondName;
	private Date arrivalDate;
	private Date leavingDate;
	private Room room;
	private ArrayList<Service> services;

	public Guest(String firstName, String secondName, Date arrivalDate, Date leavingDate) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.arrivalDate = arrivalDate;
		this.leavingDate = leavingDate;
		this.services = new ArrayList<Service>();
	}

	public Guest() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return (this.getFirstName() + " " + this.getSecondName());
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<Service> getGuestServices() {
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
		ArrayList<Service> services;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		str.append(this.getFirstName()).append(" ").append(this.getSecondName()).append(" ")
				.append(dateFormat.format(getArrivalDate())).append(" ").append(dateFormat.format(getLeavingDate()));

		str.append(" ");
		str.append(this.getRoom().getName());

		services = this.getGuestServices();
		for (int i = 0; i < services.size(); i++) {
			str.append(" ");
			str.append(services.get(i).getName());
			str.append(" ");

		}

		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (leavingDate == null) {
			if (other.leavingDate != null)
				return false;
		} else if (!leavingDate.equals(other.leavingDate))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;

		return true;
	}

}
