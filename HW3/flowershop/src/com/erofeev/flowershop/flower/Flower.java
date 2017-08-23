package com.erofeev.flowershop.flower;

public class Flower {

	private float price;
	private String color;

	public Flower(float price, String color) {

		this.price = price;
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
