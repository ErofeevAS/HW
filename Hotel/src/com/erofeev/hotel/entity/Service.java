package com.erofeev.hotel.entity;

import java.io.Serializable;

import com.erofeev.hotel.api.IEntity;

public class Service implements IEntity, Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private String name;
	private float price;

	public Service() {

	}

	public Service(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Service(int id, String name, float price) {
		super();
		this.ID = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public int getID() {
		return ID;
	}

	public void setID(int serviceId) {
		this.ID = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		String separator = " ";
		str.append("serviceId").append(separator).append(this.getID()).append(separator);
		// str.append(System.getProperty("line.separator"));
		str.append("name").append(separator).append(this.getName()).append(separator);
		// str.append(System.getProperty("line.separator"));
		str.append("price").append(separator).append(this.getPrice()).append(separator);
		// str.append(System.getProperty("line.separator"));
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
		Service other = (Service) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		return true;
	}

}
