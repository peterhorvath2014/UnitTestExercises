package com.epam.torpedo.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.config.GameConfiguration;

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
		PrintWriter out;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			// TODO parser class
			String inputLine = "HELLO " + game.getBattleFieldWidth() + " "
					+ game.getBattleFieldHeight();
			log("Server: " + inputLine);
			out.println(inputLine);

			// Fire
			// Coordinate nextAttackingCoordinate =
			// strategy.getNextAttackingCoordinate(attackHistory);
			// attackHistory.add(nextAttackingCoordinate);
			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log(game.toString());
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
			System.err.println("Could not listen on port: " + game.getServerPort());
			System.exit(1);
		}
		return serverSocket;
	}
}
