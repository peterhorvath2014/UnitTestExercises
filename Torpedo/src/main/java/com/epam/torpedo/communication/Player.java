package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.game.Game;
import com.epam.torpedo.strategies.ShootEveryCellOneByOne;
import com.epam.torpedo.util.Utility;

public abstract class Player {
	private static final Logger logger = LogManager.getLogger();
	protected Game game;

	public Game getGame() {
		return game;
	}

	public Player(GameConfiguration gameConfiguration) {
		this.game = new Game(gameConfiguration);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void gameLog(String logMessage) {
		logger.debug(this.getClass().getSimpleName() + ": " + logMessage);
	}

	protected String defend(CommunicationResources communicationResources) throws IOException {
		String messageFromOpponent = retrieveMessage(communicationResources.getIn());
		if (MessageParser.isMessageFire(messageFromOpponent)) {
			Cell cell = game.checkFireOnHome(MessageParser.parseCommandFire(messageFromOpponent));
			String answer = generateAnswer(cell);
			sendMessage(communicationResources.getOut(), answer);
		}
		return messageFromOpponent;
	}

	private String generateAnswer(Cell cell) {
		String message = MessageParser.MISSED;
		if (cell == Cell.HIT) {
			message = MessageParser.HIT;
		} else if (cell == Cell.SUNK) {
			if (game.isOpponentDone()) {
				message = MessageParser.YOU_WON;
			} else {
				message = MessageParser.SUNK;
			}
		}
		return message;
	}

	private String retrieveMessage(BufferedReader in) throws IOException {
		return in.readLine();
	}

	protected String fire(CommunicationResources communicationResources) throws IOException {
		Coordinate nextAttackingCoordinate = sendFire(communicationResources.getOut());
		String answerFromOpponent = retrieveMessage(communicationResources.getIn());
		answerFromOpponent = evaluateAnswerOnFire(nextAttackingCoordinate, answerFromOpponent);
		Utility.sleep(100);
		return answerFromOpponent;
	}

	private String evaluateAnswerOnFire(Coordinate nextAttackingCoordinate, String answerFromOpponent) {
		if (answerFromOpponent != null) {
			setGuessedOpponentBattleFieldBasedOnAnswer(nextAttackingCoordinate, answerFromOpponent);
		} else {
			answerFromOpponent = "GAME OVER";
		}
		return answerFromOpponent;
	}

	private void setGuessedOpponentBattleFieldBasedOnAnswer(Coordinate nextAttackingCoordinate,
			String answerFromOpponent) {
		if (isRegularAnswerOnFire(answerFromOpponent)) {
			game.setGuessedOpponentBattleFieldCell(nextAttackingCoordinate,
					Cell.getFieldTypeByFieldName(answerFromOpponent));
		} else if (isWonAnswerOnFire(answerFromOpponent)) {
			game.setGuessedOpponentBattleFieldCell(nextAttackingCoordinate, Cell.SUNK);
		} else {
			throw new RuntimeException("Unexpected answer from server: " + answerFromOpponent);
		}
	}

	private boolean isWonAnswerOnFire(String answerFromOpponent) {
		return answerFromOpponent.equals(MessageParser.YOU_WON);
	}

	private boolean isRegularAnswerOnFire(String answerFromOpponent) {
		return answerFromOpponent.equals(MessageParser.MISSED) || answerFromOpponent.equals(MessageParser.HIT)
				|| answerFromOpponent.equals(MessageParser.SUNK);
	}

	private Coordinate sendFire(PrintWriter out) {
		Coordinate nextAttackingCoordinate;
		try {
			nextAttackingCoordinate = game.getNextAttackingCoordinate();
			sendMessage(out, MessageParser.buildCommandFire(nextAttackingCoordinate));
			return nextAttackingCoordinate;
		} catch (Exception e) {
			sendMessage(out, MessageParser.buildCommandError(e.getMessage()));
			throw e;
		}
	}

	protected void sendMessage(PrintWriter out, String message) {
		gameLog(message);
		out.println(message);
	}

	protected void communicateWithOpponent(Socket clientSocket) {
		CommunicationResources communicationResources = createResources(clientSocket);
		try {
			configExchange(communicationResources);
			game.setStrategy(new ShootEveryCellOneByOne(game.getGameConfiguration()));
			playGame(communicationResources);
		} catch (Exception e) {
			e.printStackTrace();
		}

		closeResources(clientSocket, communicationResources);
		gameLog(game.isWon() ? "I WON" : "I LOST OR ERROR");
	}

	private CommunicationResources createResources(Socket clientSocket) {
		CommunicationResources communicationResources = new CommunicationResources();
		try {
			communicationResources.setOut(new PrintWriter(clientSocket.getOutputStream(), true));
			communicationResources.setIn(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return communicationResources;
	}

	private void closeResources(Socket clientSocket, CommunicationResources communicationResources) {
		try {
			communicationResources.closeAll();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract void configExchange(CommunicationResources communicationResources) throws IOException;

	protected void playGame(CommunicationResources communicationResources) throws IOException {
		String messageFromOpponent = "";
		do {
			messageFromOpponent = playRound(communicationResources);
		} while (!MessageParser.isGameOver(messageFromOpponent));
	}

	protected abstract String playRound(CommunicationResources communicationResources) throws IOException;

	protected String checkHomeDone(String messageFromOpponent) {
		if (game.isHomeDone() && !messageFromOpponent.equals("YOU WON")) {
			messageFromOpponent = "YOU WON";
		}
		return messageFromOpponent;
	}
}
