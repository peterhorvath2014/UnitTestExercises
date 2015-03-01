package com.epam.torpedo.communication.main;

import com.epam.torpedo.util.Utility;

public class Torpedo {

	public static void main(String[] args) {
		Thread serverThread = new Thread(new ServerThread());
		Thread clientThread = new Thread(new ClientThread());
		serverThread.start();
		Utility.sleep(1000);
		clientThread.start();

	}

}
