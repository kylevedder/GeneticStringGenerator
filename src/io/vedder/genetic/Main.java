package io.vedder.genetic;

public class Main {

	public static void main(String[] args) {
		String input = "HI";
		String goal = "HELLO WORLD!";
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ! ";

		Mutator m = new StringMutator(input, goal, alphabet);
		System.out.println(m.execute());
	}

}
