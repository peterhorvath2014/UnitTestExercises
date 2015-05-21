package com.epam.torpedo.field.ship;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShipFactoryTest {
	private ShipFactory underTest;
	
	@BeforeMethod
	public void setUp() {
		underTest = new ShipFactory();
	}
	
	@Test
	public void testCreateShipsFromFileShouldCreateShipsFromCorrectFile() {
		// GIVEN in setup
		// WHEN
		underTest.createShipsFromFile();
		// THEN
		// TODO ships
	}
	
	
}
