package com.epam.torpedo.communication.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread implements Runnable {
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
			log("From server: " + in.readLine());
			out.close();
			in.close();
			echoSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log("GAME OVER");
	}

	public void log(String logMessage) {
		System.out.println("Client: " + logMessage);
	}
}
