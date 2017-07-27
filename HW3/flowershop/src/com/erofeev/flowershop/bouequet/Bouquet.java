package com.erofeev.flowershop.bouequet;

import com.erofeev.flowershop.flower.Flower;

public class Bouquet {
	private String name;
	private int maxSize = 10;
	private int flowerCounter = 0;
	private Flower[] flowers = new Flower[maxSize];

	public Bouquet(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void increaseFlowerCounter() {
		flowerCounter++;
	}

	public int getFlowerCounter() {
		return flowerCounter;
	}

	public void addToBouquet(Flower flower) {
		flowers[getFlowerCounter()] = flower;
		increaseFlowerCounter();

	}

	public float getCost() {
		float price = 0f;

		for (int i = 0; i < flowers.length; i++) {
			if (flowers[i] == null) {
				break;
			}
			price += flowers[i].getPrice();
		}
		return price;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Price of bouquet for ").append(this.name).append(" is ").append(this.getCost()).append(" dollars");
		return str.toString();
	}

}
