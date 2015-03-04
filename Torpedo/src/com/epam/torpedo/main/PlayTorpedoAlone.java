package com.epam.torpedo.main;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.util.Utility;

public class PlayTorpedoAlone {

	public static void main(String[] args) throws InterruptedException {
		PlayTorpedoAlone playTorpedoAlone = new PlayTorpedoAlone();
		int battleFieldWidth = 100;
		int battleFieldHeight = 100;
		String serverHostname = "127.0.0.1";
		int serverPort = 4321;
		playTorpedoAlone.play(battleFieldWidth, battleFieldHeight, serverHostname, serverPort);
	}

	public void play(int battleFieldWidth, int battleFieldHeight, String serverHostname, int serverPort) throws InterruptedException {
		// TODO Should I separate gameConfiguration to server and game
		// configuration?
		GameConfiguration gameConfigurationServer = new GameConfiguration(battleFieldWidth, battleFieldHeight,
				serverHostname, serverPort);

		GameConfiguration gameConfigurationClient = new GameConfiguration(0, 0, serverHostname, serverPort);

		Thread serverThread = new Thread(new ServerPlayer(gameConfigurationServer));
		Thread clientThread = new Thread(new ClientPlayer(gameConfigurationClient));

		serverThread.start();

		Utility.sleep(100);
		clientThread.start();
		
		clientThread.join();
	}
}
