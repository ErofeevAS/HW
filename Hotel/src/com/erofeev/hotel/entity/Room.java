package com.erofeev.hotel.entity;

public class Room {
	private int stars;
	private float price;
	private int capacity;
	private boolean empty;
	private int name;


	public Room(int name, int stars, float price, int capacity) {
		super();
		this.name = name;
		this.stars = stars;
		this.price = price;
		this.capacity = capacity;
		this.empty = true;
		
	}
	

	public int getName() {
		return name;
	}

	public void setName(int name) {
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

	@Override
	public String toString() {

		StringBuilder str = new StringBuilder();
		str.append("room ¹: ").append(this.getName()).append(" stars: ").append(this.getStars()).append(" price: ")
				.append(this.getPrice()).append(" capacity: ").append(this.getCapacity());
		str.append(" ");
		str.append(this.isEmpty());
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
		//if (empty != other.empty)
			//return false;
		if (name != other.name)
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (stars != other.stars)
			return false;
		//if (status != other.status)
			//return false;
		return true;
	}
	
	

}
