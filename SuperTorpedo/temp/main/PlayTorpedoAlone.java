package com.epam.torpedo.main;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.util.Utility;

public class PlayTorpedoAlone {

	public static void main(String[] args) throws InterruptedException {
		PlayTorpedoAlone playTorpedoAlone = new PlayTorpedoAlone();
		int battleFieldWidth = 30;
		int battleFieldHeight = 30;
		String serverHostname = "127.0.0.1";
		int serverPort = 4321;
		playTorpedoAlone.play(battleFieldWidth, battleFieldHeight, serverHostname, serverPort);
	}

	public void play(int battleFieldWidth, int battleFieldHeight, String serverHostname, int serverPort) throws InterruptedException {
		GameConfiguration gameConfigurationServer = new GameConfiguration(battleFieldWidth, battleFieldHeight,
				serverHostname, serverPort, "/src/main/resources/ships.txt");

		GameConfiguration gameConfigurationClient = new GameConfiguration(0, 0, serverHostname, serverPort, "/src/main/resources/ships.txt");

		Thread serverThread = new Thread(new ServerPlayer(gameConfigurationServer), "Server");
		Thread clientThread = new Thread(new ClientPlayer(gameConfigurationClient), "Client");

		serverThread.start();

		Utility.sleep(100);
		clientThread.start();
		
		clientThread.join();
	}
}
