package com.erofeev.alexei.tank;

import com.erofeev.alexei.itank.IProduct;
import com.erofeev.alexei.itank.IProductPart;

public class Product implements IProduct {
	Body body;
	Tower tower;
	Engine engine;

	public Product() {

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
