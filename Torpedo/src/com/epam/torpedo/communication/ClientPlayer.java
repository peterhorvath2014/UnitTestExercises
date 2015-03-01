package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.game.Game;
import com.epam.torpedo.strategies.ShootEveryCellOneByOne;

public class ClientPlayer extends Player implements Runnable {

	public ClientPlayer(GameConfiguration gameConfiguration) {
		super(gameConfiguration);
	}

	@Override
	public void run() {
		log("STARTED");

		Socket clientSocket = createClient(game.getServerHost(),
				game.getServerPort());

		communicateWithServer(clientSocket);

		log("GAME OVER");
	}

	private void communicateWithServer(Socket clientSocket) {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			recieveConfigFromServer(in);

			game.setStrategy(new ShootEveryCellOneByOne(game
					.getGameConfiguration()));

			playGame(out, in);

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log(game.toString());
	}

	private void playGame(PrintWriter out, BufferedReader in)
			throws IOException {
		String messageFromServer = "";
		do {
			messageFromServer = sendFire(out, in);

			// TODO send YOU WON
			if (game.isDone()) {
				messageFromServer = "YOU WON";
			} else {
				messageFromServer = recieveFire(out, in);
			}

			// TODO ERROR check
		} while (!messageFromServer.equals("YOU WON"));
	}

	private void recieveConfigFromServer(BufferedReader in) throws IOException {
		String serverMessage = in.readLine();
		log("Config from server: " + serverMessage);
		String[] configs = serverMessage.split(" ");
		game.setBattleFieldWidth(Integer.valueOf(configs[1]));
		game.setBattleFieldHeight(Integer.valueOf(configs[2]));
		game = new Game(game.getGameConfiguration());
	}

	private Socket createClient(String serverHostname, int serverPort) {
		log("Attemping to connect to host " + serverHostname + " on port "
				+ serverPort);

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
