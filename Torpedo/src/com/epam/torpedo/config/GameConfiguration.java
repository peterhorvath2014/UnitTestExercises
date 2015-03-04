package com.epam.torpedo.config;

import com.epam.torpedo.util.Utility;

public class GameConfiguration {
	

	public int width;
	public int height;
	
	public String serverHost;
	public int serverPort;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + ((serverHost == null) ? 0 : serverHost.hashCode());
		result = prime * result + serverPort;
		result = prime * result + width;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameConfiguration other = (GameConfiguration) obj;
		if (height != other.height)
			return false;
		if (serverHost == null) {
			if (other.serverHost != null)
				return false;
		} else if (!serverHost.equals(other.serverHost))
			return false;
		if (serverPort != other.serverPort)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
	
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
