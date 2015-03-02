package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.strategies.ShootEveryCellOneByOne;

public class ServerPlayer extends Player implements Runnable {

	public ServerPlayer(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	@Override
	public void run() {
		log("STARTED");

		ServerSocket serverSocket = createServer();

		Socket clientSocket = waitForClientToConnect(serverSocket);

		communicateWithClient(serverSocket, clientSocket);

		log("GAME OVER");
	}

	private void communicateWithClient(ServerSocket serverSocket,
			Socket clientSocket) {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			sendConfigToClient(out);

			game.setStrategy(new ShootEveryCellOneByOne(game
					.getGameConfiguration()));

			playGame(out, in);

			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log(game.toString());
	}

	private void playGame(PrintWriter out, BufferedReader in)
			throws IOException {
		String messageFromOpponent = "";
		do {
			messageFromOpponent = recieveFire(out, in);
			
			messageFromOpponent = sendFire(out, in);

			if (game.isHomeDone()) {
				messageFromOpponent = "YOU WON";
			}
			// TODO ERROR check
		} while (!messageFromOpponent.equals("YOU WON") && !messageFromOpponent.equals("GAME OVER"));
	}

	private void sendConfigToClient(PrintWriter out) {
		// TODO parser class
		String message = "HELLO " + game.getBattleFieldWidth() + " "
				+ game.getBattleFieldHeight();
		sendMessage(out, message);
	}

	private Socket waitForClientToConnect(ServerSocket serverSocket) {
		Socket clientSocket = null;
		log("Waiting for connection.....");

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		log("Connection successful");
		return clientSocket;
	}

	private ServerSocket createServer() {
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
