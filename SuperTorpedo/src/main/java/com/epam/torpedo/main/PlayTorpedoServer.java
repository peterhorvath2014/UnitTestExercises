package com.epam.torpedo.main;

import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;

public class PlayTorpedoServer {

	public static void main(String[] args) throws InterruptedException {
		if (args.length != 4) {
			System.out
					.println("Usage: PlayTorpedoServer <serverHost> <serverPort> <battlefieldWidth> <battleFieldHeight>");
		}
		GameConfiguration gameConfigurationServer = new GameConfiguration(Integer.valueOf(args[2]),
				Integer.valueOf(args[3]), args[0], Integer.valueOf(args[1]));
		Thread serverThread = new Thread(new ServerPlayer(gameConfigurationServer), "Server");
		serverThread.start();
	}
}
