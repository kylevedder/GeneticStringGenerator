package io.vedder.genetic;

import java.util.Random;

public class StringMutator implements Mutator {
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
			if (Utils.levenshteinDistance(mutatedVersion, goal) <= Utils
					.levenshteinDistance(currentString, goal)) {
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

}
