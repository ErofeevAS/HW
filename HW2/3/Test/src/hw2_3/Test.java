package hw2_3;

public class Test {

	public static void main(String[] args) {
		
		IProduct product = new Product("Tank");
		
		ILineStep step1 = new LineStep();
		ILineStep step2 = new LineStep();
		ILineStep step3 = new LineStep();
		
		
		
		AssemblyLine line = new AssemblyLine();
		line.addLineStep(step1);
		line.addLineStep(step2);
		line.addLineStep(step3);
		
		line.assembleProduct(product);
		

	}

}
