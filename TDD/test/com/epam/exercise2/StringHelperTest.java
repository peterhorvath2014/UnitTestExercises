package com.epam.exercise2;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StringHelperTest {
	private StringHelper stringHelper;
	
	@BeforeMethod
	public void setUp() {
		stringHelper = new StringHelper();
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testSwapLast2CharsWhenNullThenException() {
		// GIVEN in setup
		// WHEN
		stringHelper.swapLast2Chars(null);
		// THEN throws IllegalArgumentException
	}
	
	@Test
	public void testSwapLast2CharsWhenEmptyThenReturnEmpty() {
		// GIVEN in setup
		String empty = "";
		// WHEN
		String result = stringHelper.swapLast2Chars(empty);
		// THEN
		Assert.assertEquals(result, empty);
	}
	
	@Test
	public void testSwapLast2CharsWhenOneLetterThenReturnTheSame() {
		// GIVEN in setup
		String oneLetter = "a";
		// WHEN
		String result = stringHelper.swapLast2Chars(oneLetter);
		// THEN
		Assert.assertEquals(result, oneLetter);
	}
	
	@Test
	public void testSwapLast2CharsWhenTwoLettersThenReturnSwapped1() {
		// GIVEN in setup
		String twoLetters = "ab";
		String twoLettersSwapped = "ba";
		// WHEN
		String result = stringHelper.swapLast2Chars(twoLetters);
		// THEN
		Assert.assertEquals(result, twoLettersSwapped);
	}
	
	@Test
	public void testSwapLast2CharsWhenTwoLettersThenReturnSwapped2() {
		// GIVEN in setup
		String twoLetters = "bc";
		String twoLettersSwapped = "cb";
		// WHEN
		String result = stringHelper.swapLast2Chars(twoLetters);
		// THEN
		Assert.assertEquals(result, twoLettersSwapped);
	}
	
	@Test
	public void testSwapLast2CharsWhenThreeLettersThenReturnSwappedLast2() {
		// GIVEN in setup
		String threeLetters = "abc";
		String threeLettersSwapped = "acb";
		// WHEN
		String result = stringHelper.swapLast2Chars(threeLetters);
		// THEN
		Assert.assertEquals(result, threeLettersSwapped);
	}
	
	@Test
	public void testSwapLast2CharsWhenMultipleLettersThenReturnSwappedLast2() {
		// GIVEN in setup
		String multipleLetters = "abcdef";
		String multipleLettersSwapped = "abcdfe";
		// WHEN
		String result = stringHelper.swapLast2Chars(multipleLetters);
		// THEN
		Assert.assertEquals(result, multipleLettersSwapped);
	}
}
