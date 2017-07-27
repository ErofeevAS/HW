package com.erofeev.hospital.manager;

import com.erofeev.hospital.entity.Doctor;

public class DoctorManager {

	private Doctor[] doctorsList = new Doctor[100];
	private int doctorCounter;

	public DoctorManager() {

	}

	public void setDoctorCounter() {
		doctorCounter++;
	}

	public int getDoctorCounter() {
		return doctorCounter;
	}

	public void addDoctor(Doctor doctor) {
		doctorsList[doctorCounter] = doctor;
		setDoctorCounter();

	}

	public Doctor[] getAllDoctor() {
		return doctorsList;

	}

	public void viewAllDoctors() {
		System.out.println("Doctors view:");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < doctorsList.length; i++) {
			if (doctorsList[i] == null) {
				break;
			}
			str.delete(0, str.length());
			str.append("Doctor №").append(i).append(": ").append(doctorsList[i].getFirstName()).append(" ")
					.append(doctorsList[i].getSecondName()).append(" spec: ")
					.append(doctorsList[i].getSpecialization());
			System.out.println(str);

		}

		System.out.println("Всего врачей  в больнице: " + getDoctorCounter());
		System.out.println();

	}

}
