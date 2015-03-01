package com.epam.torpedo.main;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.util.Utility;

public class Torpedo {

	public static void main(String[] args) {
		Thread serverThread = new Thread(new ServerPlayer(new GameConfiguration(10, 10)));
		Thread clientThread = new Thread(new ClientPlayer());
		
		serverThread.start();
		
		Utility.sleep(1000);
		clientThread.start();
	}

}
