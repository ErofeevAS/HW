package com.erofeev.hotel.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.erofeev.hotel.api.IEntity;

public class Guest implements IEntity, Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private String firstName;
	private String surName;
	private Date arrivalDate;
	private Date leavingDate;
	private Room room;
	private ArrayList<Service> services;

	public Guest(String firstName, String secondName, Date arrivalDate, Date leavingDate) {
		super();
		this.firstName = firstName;
		this.surName = secondName;
		this.arrivalDate = arrivalDate;
		this.leavingDate = leavingDate;
		this.services = new ArrayList<Service>();
	}

	public Guest() {

	}

	public String getName() {
		return (this.getFirstName() + " " + this.getSurName());
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

	public void setGuestServices(ArrayList<Service> guestServices) {
		this.services = guestServices;
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

	public String getSurName() {
		return surName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurName(String lastName) {
		this.surName = lastName;
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
		String separator = " ";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		str.append(this.getFirstName()).append(separator).append(this.getSurName()).append(separator)
				.append(dateFormat.format(getArrivalDate())).append(separator)
				.append(dateFormat.format(getLeavingDate()));

		str.append(separator);
		if (this.getRoom() != null) {
			str.append(this.getRoom().getID());
		}
		services = this.getGuestServices();
		for (int i = 0; i < services.size(); i++) {
			str.append(separator);
			str.append(services.get(i).getName());
			str.append(separator);

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
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;

		return true;
	}

	public int getID() {

		return this.ID;
	}

	public void setID(int id) {
		ID = id;
	}

}
