package com.erofeev.hotel.entity;

import java.io.Serializable;

import com.erofeev.hotel.api.IEntity;

public class Service implements IEntity, Serializable {

	private static final long serialVersionUID = 1L;
	private int serviceId;
	private String name;
	private float price;

	public Service(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Service() {

	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
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
		return "Service [serviceId=" + serviceId + ", name=" + name + ", price=" + price + "]";
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
