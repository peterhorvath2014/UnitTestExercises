package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.game.Game;

public class Player {
	protected Game game;

	public Player(GameConfiguration gameConfiguration) {
		// TODO decide if Game creation is needed here, because of the client
		this.game = new Game(gameConfiguration);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void log(String logMessage) {
		System.out.println(this.getClass().getSimpleName() + ": " + logMessage);
	}

	protected String recieveFire(PrintWriter out, BufferedReader in)
			throws IOException {
		String messageFromServer;
		messageFromServer = in.readLine();
		if (messageFromServer.startsWith("FIRE ")) {
			String[] answerFields = messageFromServer.split(" ");
			Cell cell = game
					.checkFire(new Coordinate(Integer.valueOf(answerFields[2]),
							Integer.valueOf(answerFields[1])));
			String message = "MISSED";
			if (cell == Cell.HIT) {
				message = "HIT";
			} else if (cell == Cell.SUNK) {
				if (game.isOpponentDone()) {
					message = "YOU WON";
				} else {
					message = "SUNK";
				}
			}
			out.println(message);
		}
		return messageFromServer;
	}

	protected String sendFire(PrintWriter out, BufferedReader in)
			throws IOException {
		String messageFromOpponent = "";
		Coordinate attackCoordinate = game.getNextAttackingCoordinate();
		String message = "FIRE " + attackCoordinate.getX() + " "
				+ attackCoordinate.getY();
		sendMessage(out, message);
		game.addAttackToHitory(attackCoordinate);
		messageFromOpponent = in.readLine();
		if (messageFromOpponent != null) {
			if (messageFromOpponent.equals("MISSED")
					|| messageFromOpponent.equals("HIT")
					|| messageFromOpponent.equals("SUNK")) {
				game.setGuessedBattleFieldCell(attackCoordinate,
						Cell.getFieldTypeByFieldName(messageFromOpponent));
			} else if (messageFromOpponent.equals("YOU WON")) {
				game.setGuessedBattleFieldCell(attackCoordinate, Cell.SUNK);
			} else {
				throw new RuntimeException("Error from server: "
						+ messageFromOpponent);
			}
		} else {
			messageFromOpponent = "GAME OVER";
		}
		return messageFromOpponent;
	}

	protected void sendMessage(PrintWriter out, String message) {
		log(message);
		out.println(message);
	}
}
