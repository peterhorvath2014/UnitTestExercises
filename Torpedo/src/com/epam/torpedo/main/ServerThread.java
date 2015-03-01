package com.epam.torpedo.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
	@Override
	public void run() {
		log("STARTED");
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(4321);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4321.");
			System.exit(1);
		}

		Socket clientSocket = null;
		log("Waiting for connection.....");

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		log("Connection successful");
		log("Waiting for input.....");

		PrintWriter out;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			String inputLine = "HELLO 100 100";
			log("Server: " + inputLine);
			out.println(inputLine);

			out.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log("GAME OVER");
	}
	
	public void log(String logMessage) {
		System.out.println("Server: " + logMessage);
	}
}
