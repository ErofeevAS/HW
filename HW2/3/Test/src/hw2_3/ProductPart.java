package hw2_3;

public class ProductPart implements IProductPart{
	private String name = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	ProductPart(String name){
		this.name = name;
	}
	
	

	
}
