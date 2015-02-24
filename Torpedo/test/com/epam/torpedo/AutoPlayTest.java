package com.epam.torpedo;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AutoPlayTest {
	
	private AutoPlay underTest;
	
	@Mock
	private Matrix enemyMatrix;
	
	@BeforeMethod
	public void setUp() {
		underTest = new AutoPlay();
		MockitoAnnotations.initMocks(this);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFireAllWhenEnemyMatrixIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		underTest.fireAll(null);
		// THEN throws Exception 
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFireAllWhenEnemyMatrixIsNotFilledThenThorwsException() {
		// GIVEN in setup
		BDDMockito.given(enemyMatrix.isFound(BDDMockito.any(Point.class))).willReturn(true);
		// WHEN
		underTest.fireAll(enemyMatrix);
		// THEN throws Exception 
	}
	
	@Test
	public void testFireAllWhenSuccessfulThenCountIsTheSame() {
		// GIVEN in setup
		BDDMockito.given(enemyMatrix.isFound(BDDMockito.any(Point.class))).willReturn(true);
		BDDMockito.given(enemyMatrix.countLiveShips()).willReturn(10);
		// WHEN
		boolean result = underTest.fireAll(enemyMatrix);
		// THEN 
		Assert.assertTrue(result);
	}
}
