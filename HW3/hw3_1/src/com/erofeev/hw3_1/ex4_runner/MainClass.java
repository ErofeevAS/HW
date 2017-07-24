package com.erofeev.hw3_1.ex4_runner;

import com.erofeev.hw3_1.ex4.ArraySort;

public class MainClass {

	public static void main(String[] args) {

		ArraySort array = new ArraySort();

		String[] testArray = { "Hello", "cruel", "World", "it", "ME", "BOB", "opop" };
		String[] capitalArray = new String[array.findCapitalWords(testArray).length];

		capitalArray = array.findCapitalWords(testArray);

		for (String string : capitalArray) {
			System.out.print(string + " ");
		}

	}

}
