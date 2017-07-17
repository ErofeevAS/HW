package com.erofeev.alexei.tank;

public class LineStepTower implements ILineStep {

	public IProductPart buildProductPart() {
		Tower tower = new Tower();
		System.out.println("Tower is ready");
		return tower;

	}

}
