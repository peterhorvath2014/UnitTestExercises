package com.epam.torpedo.config;

import org.testng.annotations.Test;

public class GameConfigurationTest {
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenFieldSizeParameterWidthIsZeroThenThorwsException() {
		// GIVEN in setup
		// WHEN
		 new GameConfiguration(0, 10);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenFieldSizeParameterHeightIsZeroThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new GameConfiguration(10, 0);
		// THEN throws Exception
	}
}
