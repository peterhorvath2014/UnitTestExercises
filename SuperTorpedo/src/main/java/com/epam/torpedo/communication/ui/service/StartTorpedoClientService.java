package com.epam.torpedo.communication.ui.service;

import org.springframework.stereotype.Service;

import com.epam.torpedo.communication.ClientPlayer;
import com.epam.torpedo.config.GameConfiguration;

@Service
public class StartTorpedoClientService {
	public GameConfiguration startTorpedoClient(GameConfiguration gameConfiguration) {
		Thread clientThread = new Thread(new ClientPlayer(gameConfiguration), "Client");
		clientThread.start();
		return gameConfiguration;
	}
}
