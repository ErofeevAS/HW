package HumanResourcesDepartment;

public class Manager extends Employee {
	
	Manager(String firstname, String lastname, int age,int id){
		super(firstname,lastname,age,id);
		
	}

	public void hire() {		
		System.out.println("Manager hiring someone...");
	}

	public void fire() {
		System.out.println("Manager firing someone...");
	}

	public void changeSalary() {
		System.out.println("Manager changing salary for someone...");

	}

	public void changeBonus() {
		System.out.println("Manager changing bonus for someone...");

	}

}