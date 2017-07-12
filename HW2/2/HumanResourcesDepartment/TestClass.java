package HumanResourcesDepartment;



class TestClass{
	
	 public static void main(String[] args) {
		
		Manager manager = new Manager("Grisha","TrueManager",25,3214);
		Cleaner cleaner = new Cleaner("Vaska","TrueCleaner",35,3214);
		Slave slave = new Slave("Petka","GoodSlave",55,2345);
		Worker worker = new Worker("Irka","Peeoner",23,6542);

		Manager manager2 = new Manager("Ivan","FOOL",23,6667);



		manager.fire();
		manager.hire();
		manager.changeBonus();
		manager.changeSalary();
		cleaner.clean();
		slave.die();
		slave.work();
		worker.work();

		System.out.println();


		Department department = new Department("RealExistingDepartment");
		Department department2 = new Department("UnRealExistingDepartment");
		department.addEmployee(manager);
		department.addEmployee(cleaner);
		department.addEmployee(slave);
		department.addEmployee(worker);
		department2.addEmployee(manager2);

		System.out.println();

		department.viewEmploee(manager);
		department.viewEmploee(cleaner);
		department.viewEmploee(slave);
		department.viewEmploee(worker);

		System.out.println();

		Organization organization = new Organization("SKAZKA");
		organization.addDepartment(department);
		organization.addDepartment(department2);







	

		
	}
}