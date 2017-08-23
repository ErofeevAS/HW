package com.erofeev.hospital.entity;

public class Person {
	private String firstName;
	private String secondName;
	private int age;
	private boolean sex;

	public Person() {

	}

	public Person(String firstName, String secondName, int age, boolean sex) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.age = age;
		this.sex = sex;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

}
