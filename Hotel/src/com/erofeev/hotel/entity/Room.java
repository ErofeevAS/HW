package com.erofeev.hotel.entity;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import com.erofeev.hotel.api.entity.IEntity;

public class Room implements IEntity, Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private int Id;
	private int stars;
	private float price;
	private int capacity;
	private boolean empty;
	private String name;
	private RoomStatus roomStatus = RoomStatus.SERVICED;
	private List<Guest> roomHistory = new ArrayList<Guest>();

	public Room() {

	}

	public Room(String name, int stars, float price, int capacity) {
		super();
		this.name = name;
		this.stars = stars;
		this.price = price;
		this.capacity = capacity;
		this.empty = true;

	}

	public Room(int id, String name, int stars, float price, int capacity, boolean empty) {
		super();
		this.Id = id;
		this.name = name;
		this.stars = stars;
		this.price = price;
		this.capacity = capacity;
		this.empty = empty;

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Guest> getRoomHistory() {
		return roomHistory;
	}

	public void setRoomHistory(List<Guest> roomHistory) {
		this.roomHistory = roomHistory;
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
		String separator = " ";
		str.append("RoomId: ").append(this.getId()).append(separator);
		str.append(this.getName()).append(separator).append(this.getStars()).append(separator).append(this.getPrice())
				.append(separator).append(this.getCapacity());
		str.append(separator);
		str.append(this.isEmpty());
		str.append(separator);
		str.append(this.getRoomStatus());
		str.append(separator);
		str.append("[");
		List<Guest> history = this.getRoomHistory();
		for (Guest guest : history) {
			str.append(guest);
			str.append(";");
		}
		str.append("]");
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

	@Override
	public Room clone() throws CloneNotSupportedException {
		Room copy =  (Room) super.clone();			
		List<Guest> copyRoomHistory = new ArrayList<Guest>(roomHistory.size());	       
		Iterator<Guest> iterator = roomHistory.iterator();
		while(iterator.hasNext()){
			Guest copyGuest = (Guest) iterator.next().clone();
			copyRoomHistory.add(copyGuest);
		}
		copy.setRoomHistory(copyRoomHistory);
		return copy;

	}

}
