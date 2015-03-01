package com.epam.torpedo.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.torpedo.config.GameConfiguration;
import com.epam.torpedo.game.Game;

public class ClientPlayer extends Player implements Runnable {

	@Override
	public void run() {
		log("STARTED");
		String serverHostname = new String("127.0.0.1");

		log("Attemping to connect to host " + serverHostname + " on port 4321.");

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			echoSocket = new Socket(serverHostname, 4321);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + serverHostname);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: "
					+ serverHostname);
			System.exit(1);
		}

		try {
			String serverAnswer = in.readLine();
			log("Config from server: " + serverAnswer);
			String[] configs = serverAnswer.split(" ");
			gameAPI = new Game(new GameConfiguration(Integer.valueOf(configs[1]), Integer.valueOf(configs[2])));
			
			out.close();
			in.close();
			echoSocket.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		log(gameAPI.toString());
		log("GAME OVER");
	}
}
