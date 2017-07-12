package hw2_3;
import java.util.ArrayList;

public class AssemblyLine implements IAssemblyLine{
	
	private ArrayList<ILineStep> list = new ArrayList<>();
	private LineStep step;
	
	public LineStep getLineStep(){
		return step;
	}
	
	public void setLineStep(){
		
	}
	
	public void addLineStep(ILineStep ls){
		list.add(ls);
	}
	
	
	
	
	
	
	public IProduct assembleProduct(IProduct p ){
		
		for(ILineStep ls:list){
			IProductPart part = ls.buildProductPart();
			if(part.getName().equals("tower"))
			{
				p.installFirstPart(part);
			}
			
			if(part.getName().equals("engine"))
			{
				p.installSecondPart(part);
			}
			
			if(part.getName().equals("body"))
			{
				p.installThirdPart(part);
			}
			
			
		}
		
		System.out.println(  "Tank is ready");
		
		return p;
	}

}
