package com.erofeev.alexei.pipeline;

import com.erofeev.alexei.itank.IAssemblyLine;
import com.erofeev.alexei.itank.IProduct;
import com.erofeev.alexei.linestep.LineStepBody;
import com.erofeev.alexei.linestep.LineStepEngine;
import com.erofeev.alexei.linestep.LineStepTower;

public class AssemblyLine implements IAssemblyLine {

	LineStepBody stepOne = new LineStepBody();
	LineStepTower stepTwo = new LineStepTower();
	LineStepEngine stepThree = new LineStepEngine();

	public IProduct assembleProduct(IProduct product) {
		product.installFirstPart(stepOne.buildProductPart());
		product.installSecondPart(stepTwo.buildProductPart());
		product.installThirdPart(stepThree.buildProductPart());
		System.out.println("Tank is ready");

		return product;
	}

}
