package com.erofeev.alexei.tank;

public class LineStepBody implements ILineStep {

	public IProductPart buildProductPart() {
		Body body = new Body();
		System.out.println("Body is ready");
		return body;

	}

}
