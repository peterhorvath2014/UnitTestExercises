package com.epam.torpedo.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.epam.torpedo.util.Utility;

public class ClientThread implements Runnable {
	@Override
	public void run() {
		System.out.println("CLIENT MODE. Connecting...");
		Utility.sleep(1000);
		String hostName = "localhost";
		int portNumber = Integer.parseInt("4321");

		try {
			System.out.println("Client: Connecting to " + hostName + " on port "
					+ portNumber);
			Socket client = new Socket(hostName, portNumber);
			System.out.println("Client: Just connected to "
					+ client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			System.out.println("Client: Writing...");
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("Client: Server says " + in.readUTF());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("CLIENT GAME OVER");
	}
}
