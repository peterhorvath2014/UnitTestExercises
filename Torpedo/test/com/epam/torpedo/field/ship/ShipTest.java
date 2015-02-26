package com.epam.torpedo.field.ship;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.torpedo.field.AbstractField;
import com.epam.torpedo.field.Coordinate;
import com.epam.torpedo.field.FieldType;

public class ShipTest {
	private AbstractField underTest;

	@BeforeMethod
	public void setUp() {
		underTest = new Ship();
	}

	@Test
	public void testAutoAddEmptyBorderWhenTopLineIsNotEmptyThenAddsEmtpyFirstLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(3, 6), FieldType.EMPTY);
		underTest.setCellFieldType(new Coordinate(0, 3), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(0, 4), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(1, 3), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(2, 3), FieldType.SHIP_PART);
		AbstractField expected = new Ship();
		expected.setUninitializedCells(new Coordinate(4, 6), FieldType.EMPTY);
		expected.setCellFieldType(new Coordinate(1, 3), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(1, 4), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(2, 3), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(3, 3), FieldType.SHIP_PART);
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
	
	@Test
	public void testAutoAddEmptyBorderWhenLeftLineIsNotEmptyThenAddsEmtpyLeftLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(3, 3), FieldType.EMPTY);
		underTest.setCellFieldType(new Coordinate(1, 0), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(2, 0), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(1, 1), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(1, 2), FieldType.SHIP_PART);
		AbstractField expected = new Ship();
		expected.setUninitializedCells(new Coordinate(3, 4), FieldType.EMPTY);
		expected.setCellFieldType(new Coordinate(1, 1), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(2, 1), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(1, 2), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(1, 3), FieldType.SHIP_PART);
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
	
	@Test
	public void testAutoAddEmptyBorderWhenRightLineIsNotEmptyThenAddsEmtpyRightLine() {
		// GIVEN in setup
		underTest.setUninitializedCells(new Coordinate(3, 3), FieldType.EMPTY);
		underTest.setCellFieldType(new Coordinate(1, 2), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(2, 2), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(1, 3), FieldType.SHIP_PART);
		underTest.setCellFieldType(new Coordinate(1, 4), FieldType.SHIP_PART);
		System.out.println(underTest);
		AbstractField expected = new Ship();
		expected.setUninitializedCells(new Coordinate(3, 4), FieldType.EMPTY);
		expected.setCellFieldType(new Coordinate(1, 1), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(2, 1), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(1, 2), FieldType.SHIP_PART);
		expected.setCellFieldType(new Coordinate(1, 3), FieldType.SHIP_PART);
		System.out.println(expected);
		// WHEN
		underTest.autoAddEmptyBorder();
		// THEN
		Assert.assertEquals(underTest, expected);
	}
}
