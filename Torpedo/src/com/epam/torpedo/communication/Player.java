package com.epam.torpedo.communication;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.game.Game;

public class Player {
	protected Game game;

	public Player(GameConfiguration gameConfiguration) {
		this.game = new Game(gameConfiguration);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void log(String logMessage) {
		System.out.println(this.getClass().getSimpleName() + ": " + logMessage);
	}
}
