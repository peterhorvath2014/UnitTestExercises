package com.epam.torpedo.communication.ui.service;

import org.springframework.stereotype.Service;

import com.epam.torpedo.communication.ServerPlayer;
import com.epam.torpedo.config.GameConfiguration;

@Service
public class StartTorpedoServerService {
	public GameConfiguration startTorpedoServer(GameConfiguration gameConfiguration) {
		Thread serverThread = new Thread(new ServerPlayer(gameConfiguration), "Server");
		serverThread.start();
		return gameConfiguration;
	}
}
