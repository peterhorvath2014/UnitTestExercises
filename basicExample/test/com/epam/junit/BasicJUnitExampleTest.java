package com.epam.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BasicJUnitExampleTest {
	private BasicJUnitExample basicJUnitExample;
	private final static Integer LESS_THAN_ULTIMATE = 41;
	private final static Integer ULTIMATE = 42;
	private final static Integer MORE_THAN_ULTIMATE = 43;
	
	@Before
	public void setUp() {
		basicJUnitExample = new BasicJUnitExample();
	}
	
	@Test
	public void testUltimateAnswerShouldTellEverythingAboutTheUniverse() {
		//GIVEN in setUp()
		//WHEN
		Integer result = basicJUnitExample.ultimateAnswer();
		//THEN
		assertEquals(ULTIMATE, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadAnswerShouldThrowAnExceptionWhenInputIsTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		basicJUnitExample.badAnswer(ULTIMATE);
		//THEN Exception thrown
	}
	
	@Test
	public void testBadAnswerShouldGiveBackInputIfLessThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = LESS_THAN_ULTIMATE;
		//THEN
		assertEquals(input, basicJUnitExample.badAnswer(input));
	}
	
	@Test
	public void testBadAnswerShouldGiveBackInputIfMoreThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = MORE_THAN_ULTIMATE;
		//THEN
		assertEquals(input, basicJUnitExample.badAnswer(input));
	}
	
	@Test
	public void testisTheUltimateAnswerShouldBeTrueIfInputIsTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = ULTIMATE;
		//THEN
		assertEquals(true, basicJUnitExample.isTheUltimateAnswer(input));
	}
	
	@Test
	public void testIsTheUltimateAnswerShouldBeFalseIfInputIsLessThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = LESS_THAN_ULTIMATE;
		//THEN
		assertEquals(false, basicJUnitExample.isTheUltimateAnswer(input));
	}
	
	@Test
	public void testIsTheUltimateAnswerShouldBeFalseIfInputIsMoreThanTheUltimateAnswer() {
		//GIVEN in setUp()
		//WHEN
		Integer input = MORE_THAN_ULTIMATE;
		//THEN
		assertEquals(false, basicJUnitExample.isTheUltimateAnswer(input));
	}
	
	
}
