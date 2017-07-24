package com.erofeev.flowershop.flower;

public class Flower {
	
	private float price;	
	private int amount;
	private String color;
	
	public Flower(float price, int amount,String color){
		
		this.price = price;		
		this.amount = amount;
		this.color = color;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	

}
