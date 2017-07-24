package com.erofeev.hw3_4.runner;

import com.erofeev.hw3_4.hospital.Doctor;
import com.erofeev.hw3_4.hospital.Hospital;
import com.erofeev.hw3_4.hospital.Pacient;

public class HospitalTest {
	public static void main(String[] args) {

		Hospital hospital = new Hospital();
		Doctor doctor1 = new Doctor("Bob", "Marley", 50, true, "MD");
		Doctor doctor2 = new Doctor("Snypes", "Wasley", 45, true, "surgeon");

		Pacient pacient1 = new Pacient("Julia", "Darkness", 30, false);
		Pacient pacient2 = new Pacient("Tom", "Crues", 50, true);
		Pacient pacient3 = new Pacient("Silvester", "Stalonne", 55, true);
		Pacient pacient4 = new Pacient("Umma", "Turmann", 50, false);

		hospital.addPacient(pacient1);
		hospital.addPacient(pacient2);
		hospital.addPacient(pacient3);
		hospital.addPacient(pacient4);

		hospital.addDoctor(doctor1);
		hospital.addDoctor(doctor2);

		hospital.enrollPacient(pacient1, doctor1);
		hospital.enrollPacient(pacient2, doctor1);

		hospital.enrollPacient(pacient1, doctor2);
		hospital.enrollPacient(pacient2, doctor2);
		hospital.enrollPacient(pacient3, doctor2);
		hospital.enrollPacient(pacient4, doctor2);

		hospital.viewAllPacients();
		hospital.viewAllDoctors();

		hospital.viewAllPacientsToDoctor(doctor1);
		hospital.viewAllPacientsToDoctor(doctor2);

		hospital.removePacientToDoctor(pacient2, doctor2);
		hospital.removePacientToDoctor(pacient1, doctor2);

		System.out.println();
		hospital.viewAllPacientsToDoctor(doctor2);

	}
}
