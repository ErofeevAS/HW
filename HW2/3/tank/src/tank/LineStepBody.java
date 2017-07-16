package tank;

public class LineStepBody implements ILineStep {

	public IProductPart buildProductPart() {
		Body body1 = new Body();
		System.out.println("Body is ready");
		return body1;

	}

}
