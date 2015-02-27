package com.epam.phorvath.exercise;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class ProperlyTestedParenthesis {
	
	public int isProperlyNested(String str) {
		
		if (str == null) {
			throw new IllegalArgumentException();
		}

		int result = 1;
		HashMap<Character, Character> validParenthesisPairs = this.getValidParenthesisPairs();
		Set<Character> validParentheses = this.getValidParentheses(validParenthesisPairs);
		Deque<Character> expected = new LinkedList<Character>();

		for (int i = 0; i < str.length(); i++) {
			char next = str.charAt(i);
			Character expect = expected.peekLast();
			if (expect != null && expect == next) {
				expected.removeLast();
			} else if (validParenthesisPairs.containsKey(next)) {
				expected.addLast(validParenthesisPairs.get(next));
			} else if (validParentheses.contains(next)) {
				result = 0;
			}
		}
		
		if (!expected.isEmpty()) {
			result = 0;
		}
		
		return result;
	}
	
	private Set<Character> getValidParentheses(HashMap<Character, Character> validParenthesisPairs) {
		Set<Character> validParentheses = new HashSet<Character>();
		for (Entry<Character, Character> parenthesis : validParenthesisPairs.entrySet()) {
			validParentheses.add(parenthesis.getKey());
			validParentheses.add(parenthesis.getValue());
		}
		return validParentheses;
	}
	
	private HashMap<Character, Character> getValidParenthesisPairs() {
		HashMap<Character, Character> validParenthesisPairs = new HashMap<Character, Character>();
		validParenthesisPairs.put('{', '}');
		validParenthesisPairs.put('(', ')');
		validParenthesisPairs.put('[', ']');
		return validParenthesisPairs;
	}
}
