package com.erofeev.hospital.entity;

public class Doctor extends Person {
	private Patient[] pacientsList = new Patient[100];
	private int pacientCounter;

	private String specialization;

	public Doctor(String firstName, String secondName, int age, boolean sex, String specialization) {
		super(firstName, secondName, age, sex);
		setSpecialization(specialization);
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setPacientCounter() {
		pacientCounter++;
	}

	public void dicreasePacientCounter() {
		pacientCounter--;
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

	public Patient[] removePacient(Patient pacient) {
		boolean flag = true;

		for (int i = 0; i < pacientsList.length; i++) {
			if (pacientsList[i] == null) {
				break;
			}

			if (flag) {
				pacientsList[i] = pacientsList[i];
				if (pacientsList[i].equals(pacient)) {
					dicreasePacientCounter();
					flag = false;
					if (!flag) {
						pacientsList[i] = pacientsList[i + 1];
					}
				}
			} else {
				pacientsList[i] = pacientsList[i + 1];
			}

		}

		return pacientsList;
	}

}
