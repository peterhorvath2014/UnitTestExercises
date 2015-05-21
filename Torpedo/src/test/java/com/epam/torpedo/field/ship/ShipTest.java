package com.epam.torpedo.field.ship;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Cell;
import com.epam.torpedo.field.Coordinate;

public class ShipTest {
	private Ship underTest;
	private Ship expected;

	@BeforeMethod
	public void setUp() {
		underTest = new Ship();
	}

	private void initExpectedForAutoAddEmptyBorder() {
		expected = new Ship();
		expected.setUninitializedCells(new Coordinate(3, 4), Cell.EMPTY);
		expected.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		expected.setCell(new Coordinate(2, 1), Cell.SHIP_PART);
		expected.setCell(new Coordinate(1, 2), Cell.SHIP_PART);
		expected.setCell(new Coordinate(1, 3), Cell.SHIP_PART);
	}
	
	private void initExpectedForGenerateDeniedCells() {
		expected = new Ship();
		expected.setUninitializedCells(new Coordinate(3, 4), Cell.EMPTY);
		expected.setCell(new Coordinate(0, 1), Cell.DENIED);
		expected.setCell(new Coordinate(0, 2), Cell.DENIED);
		expected.setCell(new Coordinate(0, 3), Cell.DENIED);

		expected.setCell(new Coordinate(1, 0), Cell.DENIED);
		expected.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		expected.setCell(new Coordinate(1, 2), Cell.SHIP_PART);
		expected.setCell(new Coordinate(1, 3), Cell.SHIP_PART);
		expected.setCell(new Coordinate(1, 4), Cell.DENIED);
		
		expected.setCell(new Coordinate(2, 0), Cell.DENIED);
		expected.setCell(new Coordinate(2, 1), Cell.SHIP_PART);
		expected.setCell(new Coordinate(2, 2), Cell.DENIED);
		expected.setCell(new Coordinate(2, 3), Cell.DENIED);
		
		expected.setCell(new Coordinate(3, 1), Cell.DENIED);
	}

	@Test
	public void testAutoAddEmptyBorderWhenTopLineIsNotEmptyThenAddsEmtpyFirstLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(2, 4), Cell.EMPTY);
		underTest.setCell(new Coordinate(0, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 2), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 3), Cell.SHIP_PART);
		initExpectedForAutoAddEmptyBorder();
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}

	@Test
	public void testAutoAddEmptyBorderWhenLeftLineIsNotEmptyThenAddsEmtpyLeftLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(3, 3), Cell.EMPTY);
		
		underTest.setCell(new Coordinate(1, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(2, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 2), Cell.SHIP_PART);
		initExpectedForAutoAddEmptyBorder();
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}

	@Test
	public void testAutoAddEmptyBorderWhenRightLineIsNotEmptyThenAddsEmtpyRightLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(3, 3), Cell.EMPTY);
		underTest.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(2, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 2), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 3), Cell.SHIP_PART);
		initExpectedForAutoAddEmptyBorder();
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}

	@Test
	public void testAutoAddEmptyBorderWhenBottomLineIsNotEmptyThenAddsEmtpyBottomLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(2, 4), Cell.EMPTY);
		underTest.setCell(new Coordinate(1, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(2, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 2), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 3), Cell.SHIP_PART);
		initExpectedForAutoAddEmptyBorder();
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
	
	@Test
	public void testAutoAddEmptyBorderWhenAllSideLineIsNotEmptyThenAddsEmtpyLines() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(2, 2), Cell.EMPTY);
		underTest.setCell(new Coordinate(0, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 2), Cell.SHIP_PART);
		initExpectedForAutoAddEmptyBorder();
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
	
	@Test
	public void testGenerateDeniedCellsWhenAllSideLineIsNotEmptyThenAddsEmtpyLinesAndDeniedCells() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(2, 2), Cell.EMPTY);
		underTest.setCell(new Coordinate(0, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(1, 0), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 1), Cell.SHIP_PART);
		underTest.setCell(new Coordinate(0, 2), Cell.SHIP_PART);
		initExpectedForGenerateDeniedCells();
		// WHEN
		underTest.generateDeniedCells();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToZeroCoordinateThenGetUnknown() {
		// GIVEN 
		underTest = new Ship();
		Coordinate coordinate = new Coordinate(0,0);
		underTest.setCell(coordinate, Cell.UNKNOWN);
		// WHEN
		Cell result = underTest.getCell(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, Cell.UNKNOWN);
	}
	
	@Test
	public void testGuessedBattleFieldWhenSetUnknownToDifferentCoordinateThenGetUnknown() {
		// GIVEN 
		underTest = new Ship();
		Coordinate coordinate = new Coordinate(3,4);
		underTest.setCell(coordinate, Cell.UNKNOWN);
		// WHEN
		Cell result = underTest.getCell(coordinate);
		// THEN throws Exception
		Assert.assertEquals(result, Cell.UNKNOWN);
	}
	
	@Test
	public void testSetUninitializedCellsWhenNewCoordinateYHigherXLowerThanBeforeThenFillsCellsHigherXThanNewCoordinate() {
		// GIVEN 
		underTest = new Ship();
		Coordinate coordinate = new Coordinate(4,4);
		Coordinate newCoordinate = new Coordinate(5,2);
		Coordinate newBottomLeftCoordinate = new Coordinate(5,4);
		Ship expectedField = new Ship();
		expectedField.setUninitializedCells(newBottomLeftCoordinate, Cell.UNKNOWN);
		// WHEN
		underTest.setUninitializedCells(coordinate, Cell.UNKNOWN);
		underTest.setUninitializedCells(newCoordinate, Cell.UNKNOWN);
		// THEN throws Exception
		Assert.assertEquals(underTest, expectedField);
	}
	
	@Test
	public void testSetUninitializedCellsWhenNewCoordinateXHigherYLowerThanBeforeThenFillsCellsHigherYThanNewCoordinate() {
		// GIVEN 
		underTest = new Ship();
		Coordinate coordinate = new Coordinate(4,4);
		Coordinate newCoordinate = new Coordinate(2,5);
		Coordinate newBottomLeftCoordinate = new Coordinate(4,5);
		Ship expectedField = new Ship();
		expectedField.setUninitializedCells(newBottomLeftCoordinate, Cell.UNKNOWN);
		// WHEN
		underTest.setUninitializedCells(coordinate, Cell.UNKNOWN);
		underTest.setUninitializedCells(newCoordinate, Cell.UNKNOWN);
		// THEN throws Exception
		Assert.assertEquals(underTest, expectedField);
	}
}
