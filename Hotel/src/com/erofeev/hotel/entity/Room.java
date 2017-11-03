package com.erofeev.hotel.entity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.erofeev.hotel.api.IEntity;

public class Room implements IEntity{
	private int stars;
	private float price;
	private int capacity;
	private boolean empty;
	private String name;
	private RoomStatus roomStatus = RoomStatus.SERVICED;

	private static final Logger roomLogger = LogManager.getLogger(Room.class);

	public Room(String name, int stars, float price, int capacity) {
		super();
		setName(name);
		setStars(stars);
		setPrice(price);
		setCapacity(capacity);
		setEmpty(true);

	}

	public String getName() {
		return name;
	}

	public void checkInPutData(int data) {
		if (((float) data) <= 0) {
			roomLogger.warn("Input data less then 0");
			//throw new IllegalArgumentException();
		} else {

		}

	}

	public void setName(String name) {		
		this.name = name;

	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.checkInPutData(stars);
		this.stars = stars;

	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.checkInPutData((int) price);
		this.price = price;

	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.checkInPutData(capacity);
		this.capacity = capacity;

	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}	
	

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();
		str.append(this.getName()).append(" ").append(this.getStars()).append(" ").append(this.getPrice()).append(" ")
				.append(this.getCapacity());
		str.append(" ");
		str.append(this.isEmpty());
		str.append(" ");
		str.append(this.getRoomStatus());
		str.append(" ");
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
		Room other = (Room) obj;
		if (capacity != other.capacity)
			return false;
		if (name != other.name)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (stars != other.stars)
			return false;
		return true;
	}

}
