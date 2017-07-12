package HumanResourcesDepartment;
import java.util.ArrayList;

public class Organization {

	private String name;
	ArrayList<Department> listDepartments = new ArrayList<>();

	Organization(String name){
		setName(name);
	}
	


	public String getName(){
		return name;
	}

	public void setName(String name ){
		this.name = name;
	}


	public void addDepartment(Department department){
		listDepartments.add(department);
		System.out.println(department.getName() + " adding Department");
	}





}