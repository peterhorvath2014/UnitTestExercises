package com.epam.torpedo.communication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.field.Coordinate;

public class MessageParser {
	private static final Logger logger = LogManager.getLogger();
	
	public static final String FIRE = "FIRE";
	public static final String MISS = "MISS";
	public static final String HIT = "HIT";
	public static final String SUNK = "SUNK";
	public static final String YOU_WON = "YOU WON";
	public static final String ERROR = "ERROR";
	public static final String GAME_OVER = "GAME OVER";
	public static final String I_WON = "I WON";
	public static final String I_LOST_OR_ERROR = "I LOST OR ERROR";
	
	

	public static Coordinate parseCommandFire(String fireMessage) {
		String[] messageFromOpponentParts = fireMessage.split(" ");
		Coordinate fireCoordinate = new Coordinate(Integer.valueOf(messageFromOpponentParts[2]),
				Integer.valueOf(messageFromOpponentParts[1]));
		return fireCoordinate;
	}

	public static String buildCommandFire(Coordinate attackCoordinate) {
		return FIRE + " " + attackCoordinate.getX() + " " + attackCoordinate.getY();
	}
	
	public static boolean isGameStillAlive(String messageFromOpponent) {
		logger.debug("messageFromOpponent: " + messageFromOpponent);
		boolean isAlive = messageFromOpponent != null && !messageFromOpponent.equals(YOU_WON) && !messageFromOpponent.startsWith(ERROR);
		logger.debug("isAlive: " + isAlive);
		return isAlive;
	}
	
	public static boolean isMessageFire(String messageFromOpponent) {
		return messageFromOpponent!= null && messageFromOpponent.startsWith(FIRE + " ");
	}

	public static String buildCommandError(String message) {
		return ERROR + " " + message;
	}
}
