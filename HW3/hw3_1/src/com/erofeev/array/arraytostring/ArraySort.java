package com.erofeev.array.arraytostring;

public class ArraySort {

	public String findCapitalWords(String[] mass) {

		StringBuilder capitalLetterString = new StringBuilder();
		int counterCapitalLetters = 0;
		int i = 0;

		for (String str : mass) {
			char[] ch = str.toCharArray();
			if (checkToCapital(ch[0])) {
				counterCapitalLetters++;
			}
		}

		for (String str : mass) {
			char[] ch = str.toCharArray();
			if (checkToCapital(ch[0])) {
				capitalLetterString.append(str);
				i++;
			}
		}

		return capitalLetterString.toString();
	}

	public boolean checkToCapital(char ch) {

		char[] capitals = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		boolean res;
		for (int i = 0; i < capitals.length; i++) {
			if (capitals[i] == ch) {
				res = true;
				return res;
			}
		}
		res = false;
		return res;

	}

}
