package com.erofeev.flowershop.flower;

public class Chrysanthemum extends Flower {
	private int freshness;

	public Chrysanthemum(float price, String color, int freshness) {
		super(price, color);
		this.freshness = freshness;
	}

	public float getPrice() {
		return super.getPrice() * freshness;
	}

}
