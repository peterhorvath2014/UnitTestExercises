package com.epam.alltogether;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class MathProviderTest {
private static MathProvider underTest;
	
	@BeforeClass
	public static void setUp() {
		underTest = new MathProvider();
	}
	
	@Test
	public void testSumShouldCallCalculateTwiceAndCallFormat() {
		// GIVEN in setup
		Sum sum = EasyMock.createMock(Sum.class);
		EasyMock.expect(sum.calculate(Integer.valueOf(0), Integer.valueOf(1))).andReturn(Integer.valueOf(1));
		EasyMock.expect(sum.calculate(Integer.valueOf(1), Integer.valueOf(1))).andReturn(Integer.valueOf(2));
		underTest.setCalc(sum);
		List<Integer> numbers = new ArrayList<Integer>();
		numbers.add(1);
		numbers.add(1);
		EasyMock.replay(sum);
		
		// WHEN
        String result = underTest.sum(numbers);

        // THEN
        EasyMock.verify(sum); //Ez nem jó, a formatter miatt, miért?
        Assert.assertEquals("[2]" , result);
	}
	
	@Test
	public void testSumShouldGiveZeroIfInputIsEmptyList() {
		// GIVEN in setup
		Sum sum = EasyMock.createMock(Sum.class);
		underTest.setCalc(sum);
		NumberFormatter formatter = EasyMock.createMock(NumberFormatter.class);
		EasyMock.expect(formatter.format(Integer.valueOf(0))).andReturn("[0]");
		List<Integer> numbers = new ArrayList<Integer>();
		EasyMock.replay(sum);
		
		// WHEN
        String result = underTest.sum(numbers);

        // THEN
        //EasyMock.verify(sum);
        Assert.assertEquals("[0]" , result);
	}
	
	@Ignore
	public void testSumShouldGiveZeroIfInputIsNull() {
		// GIVEN in setup
		Sum sum = EasyMock.createMock(Sum.class);
		underTest.setCalc(sum);
		EasyMock.replay(sum);
		
		// WHEN
        underTest.sum(null);

        // THEN should thorw IllegalArgumentException
	}
}
