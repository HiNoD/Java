package tree;

import java.util.HashMap;

public enum Operation {
	ADDITION('+'),
	SUBTRACTION('-'),
	MULTIPLICATION('*'),
	DIVISION('/');

	private final char character;

	Operation(final char character) {
		this.character = character;
	}

	private final static HashMap<Character, Operation> charsToOperations = new HashMap<Character, Operation>() {{
		put(ADDITION.character, ADDITION);
		put(SUBTRACTION.character, SUBTRACTION);
		put(MULTIPLICATION.character, MULTIPLICATION);
		put(DIVISION.character, DIVISION);
	}};

	public static Operation createFromCharacter(char character) {
		return charsToOperations.get(character);
	}
}
