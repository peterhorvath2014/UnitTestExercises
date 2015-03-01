package com.epam.torpedo.communication;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.game.Game;

public class Player {
	protected Game gameAPI;

	public Player() {
	}

	public Player(GameConfiguration gameConfiguration) {
		this.gameAPI = new Game(gameConfiguration);
	}

	public void setGameAPI(Game gameAPI) {
		this.gameAPI = gameAPI;
	}

	public void log(String logMessage) {
		System.out.println(this.getClass().getSimpleName() + ": " + logMessage);
	}
}
