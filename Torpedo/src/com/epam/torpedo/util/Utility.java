package com.epam.torpedo.util;

import java.util.Random;

public class Utility {
	
	public static final Random RANDOM = new Random();

	public static void isNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Parameter should not be null!!");
		}
	}
	
	public static void validateParameters(Object... parameters) {
		for (Object parameter : parameters) {
			Utility.isNull(parameter);
		}
	}
}