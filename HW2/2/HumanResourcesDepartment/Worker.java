package HumanResourcesDepartment;

public class Worker extends Employee {
	
	Worker(String firstname, String lastname,int age, int id){
		super(firstname,lastname,age,id);
		//this.work();
	}
	

	public void work() {
		System.out.println("Worker working");
	}

}