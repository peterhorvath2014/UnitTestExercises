package com.epam.torpedo.config;

public class GameConfiguration {
	@Override
	public String toString() {
		return "GameConfiguration [width=" + width + ", height=" + height + "]";
	}

	public final int width;
	public final int height;

	public GameConfiguration(int width, int height) {
		if ((width < 1) || (height < 1)) {
			throw new IllegalArgumentException("Field size is invalid: ("
					+ width + ", " + height + ")");
		}
		this.width = width;
		this.height = height;
	}
}
