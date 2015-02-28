package com.epam.torpedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.torpedo.util.Utility;

public class ServerThread implements Runnable {
	@Override
	public void run() {
		System.out.println("SERVER MODE. Waiting for connection...");
		ServerSocket server;
		Socket client;
		BufferedReader in;
		PrintWriter out;
		String line = "";
		try {
			server = new ServerSocket(4321);
			client = server.accept();
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
System.out.println("Server: Waiting for client to write...");
			line = in.readLine();
			System.out.println("Server's got: " + line);
			out.println("Hello 100 100");
			server.close();
		} catch (IOException e) {
			System.out.println("Could not listen on port 4321");
			System.exit(-1);
		}
		System.out.println("SERVER GAME OVER");
	}
}
