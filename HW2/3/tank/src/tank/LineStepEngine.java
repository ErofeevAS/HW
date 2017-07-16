package tank;

public class LineStepEngine implements ILineStep {

	public IProductPart buildProductPart() {
		Engine engine1 = new Engine();
		System.out.println("Engine is ready");
		return engine1;

	}

}
