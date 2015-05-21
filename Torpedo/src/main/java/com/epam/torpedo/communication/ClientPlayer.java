package com.epam.torpedo.communication;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.config.GameConfiguration;

public class ClientPlayer extends Player implements Runnable {

	public ClientPlayer(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	@Override
	public void run() {
		gameLog("STARTED");
		Socket clientSocket = createClient(game.getServerHost(), game.getServerPort());
		communicateWithOpponent(clientSocket);
		gameLog("GAME OVER");
	}

	@Override
	protected String playRound(CommunicationResources communicationResources) throws IOException {
		String messageFromOpponent;
		messageFromOpponent = fire(communicationResources);
		messageFromOpponent = checkHomeDone(messageFromOpponent);
		if (!MessageParser.isMessageYouWon(messageFromOpponent)) {
			messageFromOpponent = defend(communicationResources);
		}
		return messageFromOpponent;
	}

	@Override
	protected void configExchange(CommunicationResources communicationResources) throws IOException {
		String serverMessage = communicationResources.getIn().readLine();
		gameLog("Config from server: " + serverMessage);
		String[] configs = serverMessage.split(" ");
		game.setBattleFieldWidth(Integer.valueOf(configs[1]));
		game.setBattleFieldHeight(Integer.valueOf(configs[2]));
		// Need to reset, because we have just got the battlefield size from server
		game.resetGame();
	}

	private Socket createClient(String serverHostname, int serverPort) {
		gameLog("Attemping to connect to host " + serverHostname + " on port " + serverPort);
		Socket clientSocket = null;
		try {
			clientSocket = new Socket(serverHostname, serverPort);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: " + serverHostname);
			System.exit(1);
		}
		return clientSocket;
	}
}
