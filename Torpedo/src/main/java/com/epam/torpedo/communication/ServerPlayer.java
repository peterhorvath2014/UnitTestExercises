package com.epam.torpedo.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.config.GameConfiguration;

public class ServerPlayer extends Player implements Runnable {

	public ServerPlayer(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	@Override
	public void run() {
		gameLog("STARTED");
		ServerSocket serverSocket = createServer();
		Socket clientSocket = waitForClientToConnect(serverSocket);
		communicateWithOpponent(clientSocket);
		gameLog("GAME OVER");
	}

	@Override
	protected String playRound(CommunicationResources communicationResources) throws IOException {
		String messageFromOpponent;
		messageFromOpponent = defend(communicationResources);
		messageFromOpponent = fire(communicationResources);
		messageFromOpponent = checkHomeDone(messageFromOpponent);
		return messageFromOpponent;
	}

	@Override
	protected void configExchange(CommunicationResources communicationResources) {
		// TODO parser class
		String message = "HELLO " + game.getBattleFieldWidth() + " "
				+ game.getBattleFieldHeight();
		sendMessage(communicationResources.getOut(), message);
	}

	private Socket waitForClientToConnect(ServerSocket serverSocket) {
		Socket clientSocket = null;
		gameLog("Waiting for connection...");
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		gameLog("Connection successful");
		return clientSocket;
	}

	private ServerSocket createServer() {
		gameLog("Create server on port " + game.getServerPort());
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(game.getServerPort());
		} catch (IOException e) {
			System.err.println("Could not listen on port: "
					+ game.getServerPort());
			System.exit(1);
		}
		return serverSocket;
	}
}
