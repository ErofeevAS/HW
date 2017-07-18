package com.erofeev.alexei.linestep;

import com.erofeev.alexei.itank.ILineStep;
import com.erofeev.alexei.itank.IProductPart;
import com.erofeev.alexei.tank.Body;

public class LineStepBody implements ILineStep {

	public IProductPart buildProductPart() {
		Body body = new Body();
		System.out.println("Body is ready");
		return body;

	}

}
