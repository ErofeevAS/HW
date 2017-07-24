package com.erofeev.flowershop.flower;

public class Chrysanthemum extends Flower {
	private int freshness;

	public Chrysanthemum(float price, int amount, String color, int freshness) {
		super(price, amount, color);
		this.freshness = freshness;
	}

	public float getPrice() {
		return super.getPrice() * freshness;
	}

}
