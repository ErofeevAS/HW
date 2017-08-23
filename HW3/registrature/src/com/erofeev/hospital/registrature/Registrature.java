package com.erofeev.hospital.registrature;

import com.erofeev.hospital.entity.Doctor;
import com.erofeev.hospital.entity.Patient;
import com.erofeev.hospital.manager.DoctorManager;
import com.erofeev.hospital.manager.PatientManager;

public class Registrature {

	DoctorManager doctors = new DoctorManager();
	PatientManager patients = new PatientManager();

	public void viewAllPatientsToDoctor(Doctor doctor) {
		System.out.println("Patients to doctor view:");
		StringBuilder str = new StringBuilder();
		Patient[] pacientsListToDoctor = new Patient[100];
		pacientsListToDoctor = doctor.getAllPacient();
		for (int i = 0; i < doctor.getPacientCounter(); i++) {

			str.delete(0, str.length());
			str.append("Doctor patient №").append(i).append(": ").append(pacientsListToDoctor[i].getFirstName())
					.append(" ").append(pacientsListToDoctor[i].getSecondName());
			System.out.println(str);

		}
		System.out.println("Всего пациентов у врача: " + doctor.getPacientCounter());
		System.out.println();

	}

	public void enrollPacientToDoctor(Patient pacient, Doctor doctor) {
		doctor.addPacient(pacient);

	}

	public void removePacientToDoctor(Patient pacient, Doctor doctor) {
		doctor.removePacient(pacient);

	}

	public void addDoctor(Doctor doctor) {
		doctors.addDoctor(doctor);
	}

	public Doctor[] getAllDoctor() {
		return doctors.getAllDoctor();

	}

	public void viewAllDoctors() {
		doctors.viewAllDoctors();
	}

	public void addPacient(Patient pacient) {
		patients.addPacient(pacient);
	}

	public Patient[] getAllPacient() {
		return patients.getAllPacient();
	}

	public void viewAllPacients() {
		patients.viewAllPacients();
	}

}
