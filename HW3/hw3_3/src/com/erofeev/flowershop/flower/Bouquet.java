package com.erofeev.flowershop.flower;

public class Bouquet {
	private String name;
	private int maxSize = 10;
	private int flowerCounter = 0;
	Flower[] list = new Flower[maxSize];

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
		list[getFlowerCounter()] = flower;
		increaseFlowerCounter();

	}

	public float getCost() {
		float price = 0f;

		for (int i = 0; i < flowerCounter; i++) {
			price += list[i].getPrice() * list[i].getAmount();
		}
		flowerCounter = 0;

		return price;
	}

	@Override
	public String toString() {
		return "Price of bouquet for " + this.name + " is " + this.getCost() + " dollars";
	}

}
