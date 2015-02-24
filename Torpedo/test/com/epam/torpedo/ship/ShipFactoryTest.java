package com.epam.torpedo.ship;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.ship.OneShip;
import com.epam.torpedo.ship.Ship;
import com.epam.torpedo.ship.ShipFactory;

public class ShipFactoryTest {
	private ShipFactory underTest;
	
	@BeforeMethod
	public void setUp() {
		underTest = new ShipFactory();
	}
	
	@Test
	public void testCreateOneShipThenResultIsOneShip() {
		// GIVEN in setup
		// WHEN
		Ship result = underTest.createOneShip();
		// THEN
		Assert.assertTrue(result instanceof OneShip);
	}
	
	@Test
	public void testCreateTwoShipThenResultIsTwoShip() {
		// GIVEN in setup
		// WHEN
		Ship result = underTest.createTwoShip();
		// THEN
		Assert.assertTrue(result instanceof TwoShip);
	}
	
	@Test
	public void testCreateThreeShipThenResultIsThreeShip() {
		// GIVEN in setup
		// WHEN
		Ship result = underTest.createThreeShip();
		// THEN
		Assert.assertTrue(result instanceof ThreeShip);
	}
	
	@Test
	public void testCreateFourShipThenResultIsFourShip() {
		// GIVEN in setup
		// WHEN
		Ship result = underTest.createFourShip();
		// THEN
		Assert.assertTrue(result instanceof FourShip);
	}
	
	@Test
	public void testCreateTetrisShipThenResultIsTetrisShip() {
		// GIVEN in setup
		// WHEN
		Ship result = underTest.createTetrisShip();
		// THEN
		Assert.assertTrue(result instanceof TetrisShip);
	}
	
	
}
