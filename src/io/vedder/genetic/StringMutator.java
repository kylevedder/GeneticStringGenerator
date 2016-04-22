package io.vedder.genetic;

import java.util.Random;

public class StringMutator {
	private final String input;
	private final String goal;
	private final String alphabet;
	private final Random r;

	public StringMutator(String input, String goal, String alphabet) {
		this.input = input;
		this.goal = goal;
		this.alphabet = alphabet;
		r = new Random();
	}

	public String runGenetics() {
		String currentString = this.input;
		int numSteps = 0;
		while (!currentString.equals(goal)) {
			String mutatedVersion = mutate(currentString);
			if (computeLevenshteinDistance(mutatedVersion, goal) <= computeLevenshteinDistance(currentString, goal)) {
				currentString = mutatedVersion;
				System.out.println(currentString);
			}
			numSteps++;
		}
		System.out.printf("Number of steps: %d\n", numSteps);
		return currentString;
	}

	private String mutate(String input) {
		int operation = r.nextInt(3);

		char randChar = alphabet.charAt(r.nextInt(alphabet.length()));

		// get a random location
		int location = r.nextInt(input.length());
		String firstPart = input.substring(0, location);
		String secondPart = input.substring(location + 1, input.length());

		// randomization
		switch (operation) {
		case 0: // Insert random letter

			return firstPart + randChar + secondPart;
		case 1: // Append new letter
			return input + randChar;
		default:// Remove random letter
			return firstPart + secondPart;
		}
	}

	/*
	 * Levenshtein Distance Functions:
	 *
	 * Source:
	 * 
	 * https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/
	 * Levenshtein_distance#Java
	 */
	private int minimum(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
		int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

		for (int i = 0; i <= lhs.length(); i++)
			distance[i][0] = i;
		for (int j = 1; j <= rhs.length(); j++)
			distance[0][j] = j;

		for (int i = 1; i <= lhs.length(); i++)
			for (int j = 1; j <= rhs.length(); j++)
				distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
						distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));

		return distance[lhs.length()][rhs.length()];
	}

}
