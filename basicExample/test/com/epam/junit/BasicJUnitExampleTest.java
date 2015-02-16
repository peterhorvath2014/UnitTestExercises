package com.epam.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class BasicJUnitExampleTest {
	private static BasicJUnitExample underTest;
	private final static Integer LESS_THAN_ULTIMATE = 41;
	private final static Integer ULTIMATE = 42;
	private final static Integer MORE_THAN_ULTIMATE = 43;
	
	@BeforeClass
	public static void setUp() {
		underTest = new BasicJUnitExample();
	}
	
	@Test
	public void testUltimateAnswerShouldTellEverythingAboutTheUniverse() {
		//GIVEN in setUp()
		//WHEN
		Integer result = underTest.ultimateAnswer();
		//THEN
		assertEquals(ULTIMATE, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadAnswerShouldThrowAnExceptionWhenInputIsTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		underTest.badAnswer(ULTIMATE);
		//THEN Exception thrown
	}
	
	@Test
	public void testBadAnswerShouldGiveBackInputIfLessThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = LESS_THAN_ULTIMATE;
		//THEN
		assertEquals(input, underTest.badAnswer(input));
	}
	
	@Test
	public void testBadAnswerShouldGiveBackInputIfMoreThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = MORE_THAN_ULTIMATE;
		//THEN
		assertEquals(input, underTest.badAnswer(input));
	}
	
	@Test
	public void testIsTheUltimateAnswerShouldBeTrueIfInputIsTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = ULTIMATE;
		//THEN
		assertTrue(underTest.isTheUltimateAnswer(input));
	}
	
	@Test
	public void testIsTheUltimateAnswerShouldBeFalseIfInputIsLessThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = LESS_THAN_ULTIMATE;
		//THEN
		assertFalse(underTest.isTheUltimateAnswer(input));
	}
	
	@Test
	public void testIsTheUltimateAnswerShouldBeFalseIfInputIsNull() {
		//GIVEN in setUp()
		//WHEN
		Integer input = null;
		//THEN
		assertFalse(underTest.isTheUltimateAnswer(input));
	}
	
	
}
