package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.config.GameConfiguration;

public class ClientPlayer extends Player implements Runnable {

	public ClientPlayer(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	@Override
	public void run() {
		log("STARTED");
		
		Socket clientSocket = createClient(game.getServerHost(), game.getServerPort());

		communicateWithServer(clientSocket);
		
		log("GAME OVER");
	}

	private void communicateWithServer(Socket serverSocket) {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					serverSocket.getInputStream()));
			
			String serverAnswer = in.readLine();
			log("Config from server: " + serverAnswer);
			String[] configs = serverAnswer.split(" ");
			game.setBattleFieldWidth(Integer.valueOf(configs[1]));
			game.setBattleFieldHeight(Integer.valueOf(configs[2]));
			
			out.close();
			in.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log(game.toString());
	}

	private Socket createClient(String serverHostname, int serverPort) {
		log("Attemping to connect to host " + serverHostname + " on port " + serverPort);

		Socket serverSocket = null;

		try {
			serverSocket = new Socket(serverHostname, serverPort);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: "
					+ serverHostname);
			System.exit(1);
		}
		return serverSocket;
	}
}
