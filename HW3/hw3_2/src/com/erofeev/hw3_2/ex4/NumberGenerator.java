package com.erofeev.hw3_2.ex4;

import java.util.Random;

public class NumberGenerator {

	Random random = new Random();

	public void sumDigit() {
		int randomNumb1 = numberGeneration();
		System.out.println(randomNumb1);
		System.out.println(getDigit(randomNumb1, 0) + getDigit(randomNumb1, 1) + getDigit(randomNumb1, 2));
	}

	public int numberGeneration() {
		int randomNumb;
		while (true) {
			randomNumb = random.nextInt(999);
			if (randomNumb >= 100) {
				return randomNumb;
			}
		}
	}

	public int getDigit(int number, int index) {
		String randomString = Integer.toString(number);
		char[] mass_ch = randomString.toCharArray();
		return mass_ch[index] - '0';
	}

}
