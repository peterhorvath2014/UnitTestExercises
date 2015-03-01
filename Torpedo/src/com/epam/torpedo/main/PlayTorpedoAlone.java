package com.epam.torpedo.main;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.util.Utility;

public class PlayTorpedoAlone {

	public static void main(String[] args) {
		int battleFieldWidth = 10;
		int battleFieldHeight = 10;
		String serverHostname = "127.0.0.1";
		int serverPort = 4321;

		GameConfiguration gameConfiguration = new GameConfiguration(battleFieldWidth, battleFieldHeight,
				serverHostname, serverPort);
		
		Thread serverThread = new Thread(new ServerPlayer(gameConfiguration));
		Thread clientThread = new Thread(new ClientPlayer(gameConfiguration));

		serverThread.start();

		Utility.sleep(1000);
		clientThread.start();
	}
}
