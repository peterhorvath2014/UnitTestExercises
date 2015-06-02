package com.epam.torpedo.main;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.config.GameConfiguration;

public class PlayTorpedoClient {
	public static void main(String[] args) throws InterruptedException {
		if (args.length != 2) {
			System.out.println("Usage: PlayTorpedoClient <host> <port>");
		}
		GameConfiguration gameConfigurationClient = new GameConfiguration(0, 0, args[0], Integer.valueOf(args[1]));
		Thread clientThread = new Thread(new ClientPlayer(gameConfigurationClient), "Client");
		clientThread.start();
	}
}
