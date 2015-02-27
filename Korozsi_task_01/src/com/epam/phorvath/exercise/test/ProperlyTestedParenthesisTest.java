package com.epam.phorvath.exercise.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.phorvath.exercise.ProperlyTestedParenthesis;

public class ProperlyTestedParenthesisTest {
	private ProperlyTestedParenthesis properlyTestedParenthesis = new ProperlyTestedParenthesis();
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullParameterGivesException() {
		properlyTestedParenthesis.isProperlyNested(null);
	}

	@Test
	public void testSingleParanthesis() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("()") == 1);
	}

	@Test
	public void testEmptyString() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("") == 1);
	}

	@Test
	public void testKorozsiComplex1() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("{[()()]})]") == 0);
	}

	@Test
	public void testKorozsiComplex2() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("}{}[[]]()") == 0);
	}
	
	@Test
	public void testKorozsiComplex21() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("({}[[]]()") == 0);
	}

	@Test
	public void testKorozsiComplex3() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("{[()()]}") == 1);
	}

	@Test
	public void testCharactersInside() {
		assertTrue(properlyTestedParenthesis.isProperlyNested("a{d[f(asd)()a]d}s") == 1);
	}
}
