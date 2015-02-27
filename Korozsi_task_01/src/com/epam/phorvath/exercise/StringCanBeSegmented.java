package com.epam.phorvath.exercise;

import java.util.Set;
import java.util.TreeSet;

public class StringCanBeSegmented {
	
	
	public boolean wordBreak(String s, Set<String> dict) {
		if (s == null || !this.isDictValid(dict)) {
			throw new IllegalArgumentException();
		}
		
		Set<String> orderedDict = new TreeSet<String>(new LengthComparator());
		orderedDict.addAll(dict);
		
		boolean result = false;
		String temp = s;
		for (String word : orderedDict) {
			System.out.println(word);
			int match = temp.indexOf(word);
			if (match != -1) {
				temp = temp.replaceAll(word, "");
			}
		}
		if (temp.isEmpty()) {
			result = true;
		}
		return result;
	}

	private boolean isDictValid(Set<String> dict) {
		boolean result = true;
		if (dict == null) {
			result = false;
		} else {
			for (String word : dict) {
				if (word.isEmpty()) {
					result = false;
				}
			}
		}
		return result;
	}
	
}
