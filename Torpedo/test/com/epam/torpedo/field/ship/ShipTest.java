package com.epam.torpedo.field.ship;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.Cell;

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
}
