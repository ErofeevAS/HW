package HumanResourcesDepartment;

public class Employee {

	private int age;
	private int id;
	private float salary;
	private float bonus;
	private String firstname;
	private String secondname;
	
	Employee(String firstname, String secondname, int age, int id){
		
		setFirstname(firstname);
		setSecondname(secondname);	
		setAge(age);	
		setId(id);
		System.out.println(getSecondname() +" "+ getFirstname() + " " +  getAge() + " years" + ",ID:"+getId());		
		
	}	

	public int getAge() {
		return age;
	}		

	
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;		
	}

	
	public void setId(int id) {
		this.id = id;
	}

	public float getSalary() {
		return salary;
	}

	
	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getBonus() {
		return bonus;
	}

	
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String getFirstname() {
		return firstname;
	}

	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

}