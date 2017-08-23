package com.erofeev.alexei.linestep;

import com.erofeev.alexei.itank.ILineStep;
import com.erofeev.alexei.itank.IProductPart;
import com.erofeev.alexei.tank.Tower;

public class LineStepTower implements ILineStep {

	public IProductPart buildProductPart() {
		Tower tower = new Tower();
		System.out.println("Tower is ready");
		return tower;

	}

}
