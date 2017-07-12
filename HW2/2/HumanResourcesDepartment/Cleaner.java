package HumanResourcesDepartment;

public class Cleaner extends Employee {

	
	Cleaner(String firstname, String lastname, int age, int id){
		super(firstname,lastname,age,id);
		
	}

	public void clean() {
		System.out.println("Cleaner cleaning");
	}

}