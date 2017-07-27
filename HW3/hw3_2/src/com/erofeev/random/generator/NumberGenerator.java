package com.erofeev.random.generator;

import java.util.Random;

public class NumberGenerator {

	Random random = new Random();

	public void sumDigit() {
		int summ = 0;
		int randomNumber = numberGeneration();
		String randomString = Integer.toString(randomNumber);

		for (int i = 0; i < randomString.length(); i++) {
			summ += getDigit(randomNumber, i);
		}

		System.out.println(randomNumber);
		System.out.println(summ);

	}

	public int numberGeneration() {
		int randomNumb;
		while (true) {
			randomNumb = random.nextInt(9999);
			if (randomNumb >= 100) {
				return randomNumb;
			}
		}
	}

	public int getDigit(int number, int index) {
		String randomString = Integer.toString(number);
		char[] massChar = randomString.toCharArray();
		return massChar[index] - '0';
	}

}
