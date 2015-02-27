package com.epam.torpedo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Torpedo {

	public static void main(String[] args) {
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
			out.println("Hello 100 100");
			
			while (line != "exit") {
				line = in.readLine();
				// Send data back to client
				out.println(line);
			}
			server.close();
		} catch (IOException e) {
			System.out.println("Could not listen on port 4321");
			System.exit(-1);
		}
		System.out.println("GAME OVER");

	}

}
