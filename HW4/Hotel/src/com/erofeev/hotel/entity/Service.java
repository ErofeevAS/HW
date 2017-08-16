package com.erofeev.hotel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Service {
	private String name;
	private float price;
	private String dateStr;
	private Date date;

	public Service(String name, float price, String dateStr) {
		super();
		this.name = name;
		this.price = price;
		setDateStr(dateStr);
	}

	public String getDateStr() {
		return dateStr;
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

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.date = dateFormat.parse(dateStr);

		} catch (Exception e) {
			System.out.println("Worng format");
		}
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		str.append("Service: ").append(this.getName()).append(". Price: ").append(this.getPrice()).append(". Date: ")
				.append(dateFormat.format(getDate()));
		return str.toString();
	}

}
