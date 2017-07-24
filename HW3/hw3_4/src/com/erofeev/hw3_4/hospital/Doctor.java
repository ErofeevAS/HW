package com.erofeev.hw3_4.hospital;

public class Doctor extends Person {
	private Pacient[] pacientsList = new Pacient[100];
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

	public void addPacient(Pacient pacient) {
		pacientsList[getPacientCounter()] = pacient;
		setPacientCounter();

	}

	public Pacient[] getAllPacient() {
		return pacientsList;

	}

	public Pacient[] removePacient(Pacient pacient) {
		boolean flag = true;

		for (int i = 0; i < getPacientCounter(); i++) {
			if (flag) {
				pacientsList[i] = pacientsList[i];
				if (pacientsList[i].equals(pacient)) {

					flag = false;
				}
			} else {
				pacientsList[i] = pacientsList[i + 1];
				dicreasePacientCounter();
			}
		}

		return pacientsList;
	}

}
