package com.epam.torpedo.config;

import org.testng.annotations.Test;

public class GameConfigurationTest {
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenFieldSizeParameterWidthIsZeroThenThorwsException() {
		// GIVEN in setup
		// WHEN
		 new GameConfiguration(0, 10, "127.0.0.1", 4321);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenFieldSizeParameterHeightIsZeroThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new GameConfiguration(10, 0, "127.0.0.1", 4321);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenHostParameterIsNullThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new GameConfiguration(10, 0, null, 4321);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenHostParameterIsEmptyThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new GameConfiguration(10, 0, "", 4321);
		// THEN throws Exception
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGameConfigurationCreationWhenPortParameterIsZeroThenThorwsException() {
		// GIVEN in setup
		// WHEN
		new GameConfiguration(10, 0, "127.0.0.1", 0);
		// THEN throws Exception
	}
}
