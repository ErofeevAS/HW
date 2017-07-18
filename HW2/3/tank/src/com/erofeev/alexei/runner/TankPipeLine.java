package com.erofeev.alexei.runner;

import com.erofeev.alexei.pipeline.AssemblyLine;
import com.erofeev.alexei.tank.Product;

public class TankPipeLine {

	public static void main(String[] args) {

		Product tank = new Product();

		AssemblyLine line = new AssemblyLine();

		line.assembleProduct(tank);

	}

}
