package com.epam.exercise3;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IntegerStackTest {
private IntegerStack integerStack;
	private final Integer DEFAULT_NUMBER = Integer.valueOf(5);
	private final Integer DEFAULT_DIFFERENT_NUMBER = Integer.valueOf(6);
	
	@BeforeMethod
	public void setUp() {
		integerStack = new IntegerStack();
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testPushWhenNullThenException() {
		// GIVEN in setup
		// WHEN
		integerStack.push(null);
		// THEN throws IllegalArgumentException
	}
	
	@Test(expectedExceptions = IllegalStateException.class)
	public void testPopWhenEmptyThenException() {
		// GIVEN in setup
		// WHEN
		integerStack.pop();
		// THEN throws IllegalStateException
	}
	
	@Test
	public void testPushPopWhenValidThenPopTheSame() {
		// GIVEN in setup
		// WHEN
		integerStack.push(DEFAULT_NUMBER);
		Integer result = integerStack.pop();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, DEFAULT_NUMBER);
	}
	
	@Test
	public void testPushPopWhenNonEmptyAndPushValidThenPopTheSame() {
		// GIVEN in setup
		// WHEN
		integerStack.push(DEFAULT_NUMBER);
		integerStack.push(DEFAULT_DIFFERENT_NUMBER);
		Integer result = integerStack.pop();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, DEFAULT_DIFFERENT_NUMBER);
	}
	
	@Test
	public void testCountWhenEmptyThenZero() {
		// GIVEN in setup
		// WHEN
		int result = integerStack.count();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void testCountWhenOnePushedThenOne() {
		// GIVEN in setup
		// WHEN
		integerStack.push(DEFAULT_NUMBER);
		int result = integerStack.count();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, 1);
	}
	
	@Test
	public void testCountWhenMorePushedThenMore() {
		// GIVEN in setup
		// WHEN
		integerStack.push(DEFAULT_NUMBER);
		integerStack.push(DEFAULT_NUMBER);
		integerStack.push(DEFAULT_DIFFERENT_NUMBER);
		int result = integerStack.count();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, 3);
	}
	
	@Test
	public void testCountWhenMorePushedPopedThenCorrect() {
		// GIVEN in setup
		// WHEN
		integerStack.push(DEFAULT_NUMBER);
		integerStack.push(DEFAULT_NUMBER);
		integerStack.push(DEFAULT_DIFFERENT_NUMBER);
		integerStack.pop();
		integerStack.pop();
		int result = integerStack.count();
		// THEN throws IllegalArgumentException
		Assert.assertEquals(result, 1);
	}
}
