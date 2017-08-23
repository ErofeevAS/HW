package com.erofeev.hospital.runner;

import com.erofeev.hospital.entity.Doctor;
import com.erofeev.hospital.entity.Patient;
import com.erofeev.hospital.registrature.*;


public class RegistratureTest {
	public static void main(String[] args) {

		Registrature registrature = new Registrature();

		Doctor doctor1 = new Doctor("Bob", "Marley", 50, true, "MD");
		Doctor doctor2 = new Doctor("Snypes", "Wasley", 45, true, "surgeon");

		Patient patient1 = new Patient("Julia", "Darkness", 30, false);
		Patient patient2 = new Patient("Tom", "Crues", 50, true);
		Patient patient3 = new Patient("Silvester", "Stalonne", 55, true);
		Patient patient4 = new Patient("Umma", "Turmann", 50, false);		

		registrature.addDoctor(doctor1);
		registrature.addDoctor(doctor2);
		registrature.addPacient(patient1);
		registrature.addPacient(patient2);
		registrature.addPacient(patient3);
		registrature.addPacient(patient4);

		registrature.enrollPacientToDoctor(patient1, doctor1);
		registrature.enrollPacientToDoctor(patient2, doctor1);

		registrature.enrollPacientToDoctor(patient1, doctor2);
		registrature.enrollPacientToDoctor(patient2, doctor2);
		registrature.enrollPacientToDoctor(patient3, doctor2);
		registrature.enrollPacientToDoctor(patient4, doctor2);

		registrature.viewAllPacients();
		registrature.viewAllDoctors();

		registrature.viewAllPatientsToDoctor(doctor1);
		registrature.viewAllPatientsToDoctor(doctor2);

		registrature.removePacientToDoctor(patient1, doctor2);
		registrature.removePacientToDoctor(patient4, doctor2);
		
		
		registrature.viewAllPatientsToDoctor(doctor2);

	}
}
