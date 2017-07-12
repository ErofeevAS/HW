package HumanResourcesDepartment;
import java.util.ArrayList;

public class Department {
	

	private String name;
	ArrayList<Employee> list = new ArrayList<>();

	Department(String name){
		setName(name);
		System.out.println( getName() + " was created");

	}
	

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addEmployee(Employee employee){
		list.add(employee);
		System.out.println(employee.getClass() + " adding to Department");
	}

	public void viewEmploee(Employee employee){
		for ( Employee e: list ) {
			if(e.equals(employee)){
				System.out.println(e.getClass().getName()+" "+ e.getFirstname()+" " +e.getSecondname()+" "+e.getId());
			}
			
		}
	}

}