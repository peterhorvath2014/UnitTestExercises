package com.epam.exercise2;

public class StringHelper {

	public String swapLast2Chars(String str) {
		validateNotNull(str);
		String result = "";
		if (!str.isEmpty()) {
			if (str.length() == 1) {
				result = str;
			} else {
				result = swapLast2CharsAtLeastTwoLetters(str);
			}
		}
		return result;
	}

	private String swapLast2CharsAtLeastTwoLetters(String str) {
		final int length = str.length();
		int lastCharacterPos = length - 1;
		int beforeLastCharacterPos = length - 2;
		
		final String prefix = str.substring(0, beforeLastCharacterPos);
		final char lastChar = str.charAt(lastCharacterPos);
		final char beforeLastChar = str.charAt(beforeLastCharacterPos);
		final String result = prefix + lastChar + beforeLastChar;
		
		return result;
	}

	private void validateNotNull(String str) {
		if (str == null) {
			throw new IllegalArgumentException();
		}
	}

}
