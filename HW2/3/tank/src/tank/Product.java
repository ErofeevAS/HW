package tank;

public class Product implements IProduct {
	static int x = 0;

	Product() {
		x++;
	}

	IProductPart[] parts = new IProductPart[3];

	public void installFirstPart(IProductPart pp) {
		parts[0] = pp;
		System.out.println("Body is installed ");

	}

	public void installSecondPart(IProductPart pp) {
		parts[1] = pp;
		System.out.println("Tower is installed ");

	}

	public void installThirdPart(IProductPart pp) {
		parts[2] = pp;
		System.out.println("Engine is installed ");

	}

}
