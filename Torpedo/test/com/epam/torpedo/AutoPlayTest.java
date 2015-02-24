package com.epam.torpedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AutoPlayTest {
	
	@Test
	public void testFireAllWhenSuccessfulThenCountIsTheSame() {
		// GIVEN
		AutoPlay underTest = new AutoPlay();
		Matrix enemyMatrix = new DefaultMatrix();
		enemyMatrix.fillRandomShips();
		// WHEN
		Matrix ownMatrix = underTest.fireAll(enemyMatrix);
		// THEN 
		Assert.assertEquals(ownMatrix.countLiveShips(), enemyMatrix.countLiveShips());
	}
}
