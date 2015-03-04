package com.epam.torpedo.main;

import org.testng.annotations.Test;

public class PlayTorpedoAloneTest {
	
	@Test
	public void testPlayWhenAllParameterIsCorrect() throws InterruptedException {
		PlayTorpedoAlone underTest = new PlayTorpedoAlone();
		int battleFieldWidth = 100;
		int battleFieldHeight = 100;
		String serverHostname = "127.0.0.1";
		int serverPort = 4321;
		underTest.play(battleFieldWidth, battleFieldHeight, serverHostname, serverPort);
	}
}
