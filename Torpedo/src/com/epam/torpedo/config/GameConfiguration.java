package com.epam.torpedo.config;

import com.epam.torpedo.util.Utility;

public class GameConfiguration {
	
	public int width;
	public int height;
	
	public String serverHost;
	public int serverPort;
	
	@Override
	public String toString() {
		return "GameConfiguration [width=" + width + ", height=" + height + "]";
	}

	public GameConfiguration(int width, int height, String serverHost, int serverPort) {
		validateParameters(width, height, serverHost, serverPort);
		this.width = width;
		this.height = height;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	private void validateParameters(int width, int height, String serverHost,
			int serverPort) {
		if ((width < 0) || (height < 0)) {
			throw new IllegalArgumentException("Field size is invalid: ("
					+ width + ", " + height + ")");
		}
		Utility.isParameterNull(serverHost);
		if (serverHost.isEmpty()) {
			throw new IllegalArgumentException("ServerHost cannot be empty");
		}
		if (serverPort<1025) {
			throw new IllegalArgumentException("ServerPost must be greater than 1024");
		}
	}
}
