package com.erofeev.alexei.tank;

public class LineStepEngine implements ILineStep {

	public IProductPart buildProductPart() {
		Engine engine = new Engine();
		System.out.println("Engine is ready");
		return engine;

	}

}
