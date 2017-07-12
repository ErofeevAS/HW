package HumanResourcesDepartment;

public class Slave extends Employee {
	
	Slave(String firstname, String lastname,int age, int id){
		super(firstname,lastname,age,id);		
		//this.work();
		//this.die();
	}

	public void work() {
		System.out.println("Slave working");
		
	}

	public void die() {
		System.out.println("then Slave dying");
		
	}

}