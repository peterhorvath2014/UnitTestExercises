package com.epam.torpedo.config;

import org.springframework.stereotype.Repository;

import com.epam.torpedo.util.Utility;

@Repository
public class GameConfiguration {
	public String serverHost;
	public int serverPort;
	public int width;
	public int height;
	public String shipsFilePath;

	public GameConfiguration(int width, int height, String serverHost, int serverPort, String shipsFilePath) {
		validateParameters(width, height, serverHost, serverPort, shipsFilePath);
		this.width = width;
		this.height = height;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.shipsFilePath = shipsFilePath;
	}

	public GameConfiguration() {
	}

	/**
	 * @return the serverHost
	 */
	public String getServerHost() {
		return serverHost;
	}

	/**
	 * @param serverHost
	 *            the serverHost to set
	 */
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
	}

	/**
	 * @param serverPort
	 *            the serverPort to set
	 */
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the shipsFilePath
	 */
	public String getShipsFilePath() {
		return shipsFilePath;
	}

	/**
	 * @param shipsFilePath
	 *            the shipsFilePath to set
	 */
	public void setShipsFilePath(String shipsFilePath) {
		this.shipsFilePath = shipsFilePath;
	}

	private void validateParameters(int width, int height, String serverHost, int serverPort, String shipsFilePath) {
		if ((width < 0) || (height < 0)) {
			throw new IllegalArgumentException("Field size is invalid: (" + width + ", " + height + ")");
		}
		Utility.isParameterNull(serverHost);
		if (serverHost.isEmpty()) {
			throw new IllegalArgumentException("ServerHost cannot be empty");
		}
		if (serverPort < 1025) {
			throw new IllegalArgumentException("ServerPort must be greater than 1024");
		}
		Utility.isParameterNull(shipsFilePath);
	}

	@Override
	public String toString() {
		return "GameConfiguration [serverHost=" + serverHost + ", serverPort=" + serverPort + ", width=" + width
				+ ", height=" + height + ", shipsFilePath=" + shipsFilePath + "]";
	}

}
