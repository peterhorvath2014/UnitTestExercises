package com.epam.torpedo.communication;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.game.Game;

public class ClientPlayerTest {
	
	GameConfiguration gameConfiguration;
	
	@BeforeMethod
	public void setUp() {
		int battleFieldWidth = 0;
		int battleFieldHeight = 0;
		String serverHostname = "127.0.0.1";
		int serverPort = 4321;

		gameConfiguration = new GameConfiguration(battleFieldWidth, battleFieldHeight,
				serverHostname, serverPort);
	}
	
	@Test
	public void testClientPlayerWhenCreateThenGameCreated() {
		// GIVEN in setup
		// WHEN
		ClientPlayer underTest = new ClientPlayer(gameConfiguration);
		Game expectedGame = new Game(gameConfiguration);
		// THEN
		Assert.assertEquals(underTest.getGame(), expectedGame);
	}
}
