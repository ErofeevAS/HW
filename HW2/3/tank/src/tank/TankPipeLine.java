package tank;

public class TankPipeLine {

	public static void main(String[] args) {

		Product tank = new Product();

		AssemblyLine line = new AssemblyLine();

		line.assembleProduct(tank);

	}

}
