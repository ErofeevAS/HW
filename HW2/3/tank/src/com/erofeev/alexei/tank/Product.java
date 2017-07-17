package com.erofeev.alexei.tank;

public class Product implements IProduct {
	Body body;
	Tower tower;
	Engine engine;

	Product() {

	}

	public void installFirstPart(IProductPart productpart) {
		body = (Body) productpart;
		System.out.println("Body is installed ");

	}

	public void installSecondPart(IProductPart productpart) {
		tower = (Tower) productpart;
		System.out.println("Tower is installed ");

	}

	public void installThirdPart(IProductPart productpart) {
		engine = (Engine) productpart;
		System.out.println("Engine is installed ");

	}

}
