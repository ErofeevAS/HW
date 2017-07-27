package com.erofeev.array.runner;

import com.erofeev.array.arraytostring.ArraySort;

public class MainClass {

	public static void main(String[] args) {

		ArraySort array = new ArraySort();

		String[] testArray = { "Hello", "cruel", "World", "it", "ME", "BOB", "opop" };

		System.out.println(array.findCapitalWords(testArray));

	}

}
