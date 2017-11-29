package com.erofeev.hotel.entity;

import java.io.Serializable;

import com.erofeev.hotel.api.IEntity;

public class Room implements IEntity, Serializable {
	private int stars;
	private float price;
	private int capacity;
	private boolean empty;
	private String name;
	private RoomStatus roomStatus = RoomStatus.SERVICED;

	public Room(String name, int stars, float price, int capacity) {
		super();
		this.name = name;
		this.stars = stars;
		this.price = price;
		this.capacity = capacity;
		this.empty = true;

	}

	public Room() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {

		this.stars = stars;

	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;

	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
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

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (roomStatus != other.roomStatus)
			return false;
		if (stars != other.stars)
			return false;
		return true;
	}

}
