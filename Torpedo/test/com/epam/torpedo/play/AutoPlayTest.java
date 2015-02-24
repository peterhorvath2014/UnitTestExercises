package com.epam.torpedo.play;

import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.BattleField;
import com.epam.torpedo.field.FieldCoordinate;
import com.epam.torpedo.play.AutoPlay;

public class AutoPlayTest {
	
	private AutoPlay underTest;
	
	@Mock
	private BattleField enemyMatrix;
	
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
	
	@Test
	public void testFireAllWhenSuccessfulThenCountIsTheSame() {
		// GIVEN in setup
		BDDMockito.given(enemyMatrix.isFound(BDDMockito.any(FieldCoordinate.class))).willReturn(true);
		// WHEN
		boolean result = underTest.fireAll(enemyMatrix);
		// THEN 
		Assert.assertTrue(result);
	}
}
