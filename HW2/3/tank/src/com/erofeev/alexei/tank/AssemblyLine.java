package com.erofeev.alexei.tank;

public class AssemblyLine implements IAssemblyLine {

	LineStepBody step1 = new LineStepBody();
	LineStepTower step2 = new LineStepTower();
	LineStepEngine step3 = new LineStepEngine();

	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(step1.buildProductPart());
		product.installSecondPart(step2.buildProductPart());
		product.installThirdPart(step3.buildProductPart());
		System.out.println("Tank is ready");

		return product;
	}

}
