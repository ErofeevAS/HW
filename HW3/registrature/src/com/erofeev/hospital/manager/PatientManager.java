package com.erofeev.hospital.manager;

import com.erofeev.hospital.entity.Patient;

public class PatientManager {
	private Patient[] pacientsList = new Patient[100];
	private int pacientCounter;

	public PatientManager() {

	}

	public void setPacientCounter() {
		pacientCounter++;
	}

	public int getPacientCounter() {
		return pacientCounter;
	}

	public void addPacient(Patient pacient) {
		pacientsList[getPacientCounter()] = pacient;
		setPacientCounter();

	}

	public Patient[] getAllPacient() {
		return pacientsList;

	}

	public void viewAllPacients() {
		System.out.println("Pacients view:");
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < pacientsList.length; i++) {
			if (pacientsList[i] == null) {
				break;
			}
			str.delete(0, str.length());
			str.append("Pacient №").append(i).append(": ").append(pacientsList[i].getFirstName()).append(" ")
					.append(pacientsList[i].getSecondName());

			System.out.println(str);

		}
		System.out.println("Всего пацинтов в больнице: " + getPacientCounter());
		System.out.println();

	}

}
