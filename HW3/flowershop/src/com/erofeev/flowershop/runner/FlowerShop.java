package com.erofeev.flowershop.runner;

import com.erofeev.flowershop.bouequet.Bouquet;
import com.erofeev.flowershop.flower.*;

public class FlowerShop {

	public static void main(String[] args) {

		Rose rose = new Rose(2, "Red", 2);
		Lily lily = new Lily(1, "White", 1);
		Chrysanthemum chrysanthemum = new Chrysanthemum(1, "White", 1);

		Bouquet bouquet = new Bouquet("BirthDay");
		bouquet.addToBouquet(rose);
		bouquet.addToBouquet(lily);
		bouquet.addToBouquet(chrysanthemum);
		bouquet.addToBouquet(rose);

		System.out.println(bouquet);

		Bouquet bouquet2 = new Bouquet("Wedding");
		bouquet2.addToBouquet(rose);
		bouquet2.addToBouquet(lily);
		bouquet2.addToBouquet(chrysanthemum);
		bouquet2.addToBouquet(chrysanthemum);
		System.out.println(bouquet2);

	}

}
