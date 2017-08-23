package com.erofeev.hotel.entity;

import java.util.*;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Guest {
	private String fio;
	private String inDateStr;
	private String outDateStr;
	private Date inDate;
	private Date outDate;

	public Guest(String fio, String inDateStr, String outDateStr) {
		super();
		this.fio = fio;
		setInDateStr(inDateStr);
		setOutDateStr(outDateStr);
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public Date getInDate() {
		return inDate;
	}

	public String getOutDateStr() {
		return outDateStr;
	}

	public void setOutDateStr(String outDateStr) {
		this.outDateStr = outDateStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.outDate = dateFormat.parse(outDateStr);
		} catch (Exception e) {
			System.out.println("Worng data format");
		}
	}

	public String getInDateStr() {
		return inDateStr;
	}

	public void setInDateStr(String inDateStr) {
		this.inDateStr = inDateStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			this.inDate = dateFormat.parse(inDateStr);

		} catch (Exception e) {
			System.out.println("Worng format");
		}
	}

	public Date getOutDate() {
		return outDate;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		str.append("Guest name: ").append(this.getFio()).append(". Date in: ").append(dateFormat.format(getInDate()))
				.append(". Date out: ").append(dateFormat.format(getOutDate()));

		return str.toString();
	}

}
