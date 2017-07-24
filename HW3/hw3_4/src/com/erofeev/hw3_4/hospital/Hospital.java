package com.erofeev.hw3_4.hospital;

public class Hospital {
	private Pacient[] pacientsList = new Pacient[100];
	private Doctor[] doctorsList = new Doctor[100];
	private int pacientCounter;
	private int doctorCounter;

	public void setPacientCounter() {
		pacientCounter++;
	}

	public int getPacientCounter() {
		return pacientCounter;
	}

	public void setDoctorCounter() {
		doctorCounter++;
	}

	public int getDoctorCounter() {
		return doctorCounter;
	}

	public void addPacient(Pacient pacient) {
		pacientsList[getPacientCounter()] = pacient;
		setPacientCounter();

	}

	public void addDoctor(Doctor doctor) {
		doctorsList[doctorCounter] = doctor;
		setDoctorCounter();

	}

	public Pacient[] getAllPacient() {
		return pacientsList;

	}

	public Doctor[] getAllDoctor() {
		return doctorsList;

	}

	public Pacient[] getAllPacientToDoctor(Doctor doctor) {

		return doctor.getAllPacient();
	}

	public void enrollPacient(Pacient pacient, Doctor doctor) {
		doctor.addPacient(pacient);

	}

	public void removePacientToDoctor(Pacient pacient, Doctor doctor) {

		doctor.removePacient(pacient);

	}

	public void viewAllPacients() {
		for (int i = 0; i < getPacientCounter(); i++) {
			System.out.println(
					"Pacient №" + i + ": " + pacientsList[i].getFirstName() + " " + pacientsList[i].getSecondName());

		}
		System.out.println("Всего пацинтов в больнице: " + getPacientCounter());
		System.out.println();

	}

	public void viewAllDoctors() {
		for (int i = 0; i < getDoctorCounter(); i++) {
			System.out.println("Doctor №" + i + ": " + doctorsList[i].getFirstName() + " "
					+ doctorsList[i].getSecondName() + " spec: " + doctorsList[i].getSpecialization());

		}
		System.out.println("Всего врачей  в больнице: " + getDoctorCounter());
		System.out.println();

	}

	public void viewAllPacientsToDoctor(Doctor doctor) {
		Pacient[] pacientsListToDoctor = new Pacient[100];
		pacientsListToDoctor = doctor.getAllPacient();
		for (int i = 0; i < doctor.getPacientCounter(); i++) {
			System.out.println("Doctor №" + i + ": " + pacientsListToDoctor[i].getFirstName() + " "
					+ pacientsListToDoctor[i].getSecondName());
		}
		System.out.println("Всего пациентов у врача: " + doctor.getPacientCounter());
		System.out.println();

	}

}
