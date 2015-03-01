package com.epam.torpedo.game;

import java.util.LinkedList;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.battlefield.OwnedBattleField;
import com.epam.torpedo.strategies.Strategy;

public class Game {
	protected GameConfiguration gameConfiguration;
	protected GameState gameState;
	protected Strategy strategy;
	protected LinkedList<Coordinate> attackHistory; 
	
	public Game(GameConfiguration gameConfiguration) {
		this.gameConfiguration = gameConfiguration;
		attackHistory = new LinkedList<Coordinate>();
		OwnedBattleField ownedBattleField = new OwnedBattleField(gameConfiguration);
		gameState = new GameState(ownedBattleField, this.gameConfiguration);
	}

	public int getBattleFieldWidth() {
		return gameConfiguration.width;
	}
	public int getBattleFieldHeight() {
		return gameConfiguration.height;
	}
	
	@Override
	public String toString() {
		return "GameAPI [gameConfiguration=" + gameConfiguration
				+ ", gameState=" + gameState + ", strategy=" + strategy
				+ ", attackHistory=" + attackHistory + "]";
	}

	
	/*public Cell shootNext() {
		return null;
		Coordinate coordinate = strategy.getNextAttackingCoordinate(attackHistory);
		evaluatePoint(opponentAPI, coordinate);
		gameState.increaseAttackCount();
		if (gameState.isDone()) {
			gameState.setWon(true);
		}
	}*/
	
	/*protected void evaluatePoint(OpponentAPI opponentAPI, Coordinate coordinate) {
		Cell result = opponentAPI.shoot(coordinate);
		gameState.setGuessedBattleFieldCell(coordinate, result);
	}*/
}
