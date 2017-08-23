package com.erofeev.alexei.linestep;

import com.erofeev.alexei.itank.ILineStep;
import com.erofeev.alexei.itank.IProductPart;
import com.erofeev.alexei.tank.Engine;

public class LineStepEngine implements ILineStep {

	public IProductPart buildProductPart() {
		Engine engine = new Engine();
		System.out.println("Engine is ready");
		return engine;

	}

}
