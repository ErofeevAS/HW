package hw2_3;

import java.util.ArrayList;

public  class Product implements IProduct{
	
	private String name = "";
	private ArrayList<IProductPart> list = new ArrayList<>();	

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	Product(String name){
		this.name = name;
	}
	
	
	public void installFirstPart(IProductPart pp){
		System.out.println(  "Tower is installed ");
		list.add(pp);
		
	}
	public void installSecondPart(IProductPart pp){
		System.out.println( "Engine is installed ");
		list.add(pp);
	}
	public void installThirdPart(IProductPart pp){
		System.out.println( "Body is installed ");
		list.add(pp);
	}
	
	
	
	

}
