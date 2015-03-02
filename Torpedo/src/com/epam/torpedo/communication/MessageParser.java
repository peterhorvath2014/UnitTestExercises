package com.epam.torpedo.communication;

import com.epam.torpedo.field.Coordinate;

public class MessageParser {
	public static final String FIRE = "FIRE";
	public static final String MISSED = "MISSED";
	public static final String HIT = "HIT";
	public static final String SUNK = "SUNK";
	public static final String YOU_WON = "YOU WON";

	public static Coordinate parseCommandFire(String fireMessage) {
		String[] messageFromOpponentParts = fireMessage.split(" ");
		Coordinate fireCoordinate = new Coordinate(Integer.valueOf(messageFromOpponentParts[2]),
				Integer.valueOf(messageFromOpponentParts[1]));
		return fireCoordinate;
	}

	public static String buildCommandFire(Coordinate attackCoordinate) {
		return FIRE + " " + attackCoordinate.getX() + " " + attackCoordinate.getY();
	}
	
	public static boolean isMessageYouWon(String messageFromOpponent) {
		return messageFromOpponent.equals(YOU_WON);
	}
	
	public static boolean isMessageFire(String messageFromOpponent) {
		return messageFromOpponent.startsWith(FIRE + " ");
	}

	public static boolean isGameOver(String messageFromOpponent) {
		return (messageFromOpponent.equals("YOU WON") || messageFromOpponent.equals("GAME OVER"));
	}
}
