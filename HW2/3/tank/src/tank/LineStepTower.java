package tank;

public class LineStepTower implements ILineStep {

	public IProductPart buildProductPart() {
		Tower tower1 = new Tower();
		System.out.println("Tower is ready");
		return tower1;

	}

}
