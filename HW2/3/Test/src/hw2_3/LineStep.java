package hw2_3;

public class LineStep implements ILineStep{
	static int cnt = 0;
	
	public IProductPart buildProductPart(){
		cnt++;
		cnt= cnt % 3;
		
		
		ProductPart body = new ProductPart("body");
		ProductPart tower = new ProductPart("tower");
		ProductPart engine = new ProductPart("engine");
		
		switch(cnt)
	      {
	         case 0 :
	        	 System.out.println("Body is built!"); 
	             return body;
	         case 1 :
	        	 System.out.println("Tower is built!"); 
	        	 return tower;
	         
	         case 2 :
	        	 System.out.println("Engine is built!"); 
	        	 return engine;
	         
	         default :
	            System.out.println("error");
	            return null;
	      }
				
		
	}

}
