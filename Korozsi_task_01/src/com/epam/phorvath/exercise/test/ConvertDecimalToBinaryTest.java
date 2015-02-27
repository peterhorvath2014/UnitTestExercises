package com.epam.phorvath.exercise.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.phorvath.exercise.ConvertDecimalToBinary;

public class ConvertDecimalToBinaryTest {
	private static ConvertDecimalToBinary convertDecimalToBinary;

	@BeforeClass
	public static void oneTimeSetUp() {
		convertDecimalToBinary = new ConvertDecimalToBinary();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativeParameterThrowsException() {
		convertDecimalToBinary.getBinaryFormat(-5);
	}
	
	@Test
	public void testZeroValid() {
		assertEquals("0", convertDecimalToBinary.getBinaryFormat(0));
	}
	
	@Test
	public void testOneValid() {
		assertEquals("1", convertDecimalToBinary.getBinaryFormat(1));
	}
	
	@Test
	public void testTwoValid() {
		assertEquals("10", convertDecimalToBinary.getBinaryFormat(2));
	}
	
	@Test
	public void testNineValid() {
		assertEquals("1001", convertDecimalToBinary.getBinaryFormat(9));
	}
}
