package com.epam.phorvath.exercise.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.phorvath.exercise.StringCanBeSegmented;

public class StringCanBeSegmentedTest {
	private static StringCanBeSegmented stringCanBeSegmented;
	private static Set<String> defaultDict;
	private static Set<String> dictWithEmptyString;
	private static Set<String> dictWithoOerlappingStrings;
	private static Set<String> dictWithoOerlappingStrings2;
	private static String defaultStr;

	@BeforeClass
	public static void oneTimeSetUp() {
		stringCanBeSegmented = new StringCanBeSegmented();
		defaultDict = new HashSet<String>();
		defaultDict.add("leet");
		defaultDict.add("code");
		defaultStr = "leetcode";
		
		dictWithEmptyString = new HashSet<String>();
		dictWithEmptyString.add("leet");
		dictWithEmptyString.add("code");
		dictWithEmptyString.add("");
		
		dictWithoOerlappingStrings = new HashSet<String>();
		dictWithoOerlappingStrings.add("le");
		dictWithoOerlappingStrings.add("leet");
		dictWithoOerlappingStrings.add("code");
		
		dictWithoOerlappingStrings2 = new HashSet<String>();
		dictWithoOerlappingStrings2.add("a");
		dictWithoOerlappingStrings2.add("b");
		dictWithoOerlappingStrings2.add("c");
		dictWithoOerlappingStrings2.add("bcdefg");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullAllParameterGivesException() {
		stringCanBeSegmented.wordBreak(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullFirstParameterGivesException() {

		stringCanBeSegmented.wordBreak(null, defaultDict);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullSecondParameterGivesException() {
		stringCanBeSegmented.wordBreak(defaultStr, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEmptyStringDictionary() {
		stringCanBeSegmented.wordBreak("leetacode", dictWithEmptyString);
	}
	
	@Test
	public void testValidCase() {
		assertTrue(stringCanBeSegmented.wordBreak(defaultStr, defaultDict));
	}

	@Test
	public void testExtraInvalidCharacter() {
		assertFalse(stringCanBeSegmented.wordBreak("leetacode", defaultDict));
	}

	@Test
	public void testMultipleValidWords() {
		assertTrue(stringCanBeSegmented.wordBreak("leetleetcodeleetcode",
				defaultDict));
	}

	@Test
	public void testEmptyString() {
		assertTrue(stringCanBeSegmented.wordBreak("", defaultDict));
	}
	
	@Test
	public void testOverlappingDict() {
		assertTrue(stringCanBeSegmented.wordBreak(defaultStr, dictWithoOerlappingStrings));
	}
	
	@Test
	public void testOverlappingDict2() {
		assertTrue(stringCanBeSegmented.wordBreak("abcdefg", dictWithoOerlappingStrings2));
	}
	
	@Test
	public void testOverlappingDict3() {
		Set<String> dict = new HashSet<String>();
	    dict.add("bcdefg");
	    dict.add("abc");
	    dict.add("defg");
		assertTrue(stringCanBeSegmented.wordBreak("abcdefg", dict));
	}
}
