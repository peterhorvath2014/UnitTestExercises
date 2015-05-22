package com.epam.autoint;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FindDuplicateTest {

	private FindDuplicate underTest;

	@BeforeMethod
	public void setup() {
		underTest = new FindDuplicate();
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void findDuplicateWhenNullParamThrowsExceptionTest() {
		//GIVEN
		//WHEN
		underTest.findDuplicate(null);
		//THEN throws IllegalArgumentException
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void findDuplicateWhenEmptyParamThrowsExceptionTest() {
		//GIVEN
		int[] inputArray = {};
		//WHEN
		underTest.findDuplicate(inputArray);
		//THEN
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void findDuplicateWhenLengthIsOneParamThrowsExceptionTest() {
		//GIVEN
		int[] inputArray = {1};
		//WHEN
		underTest.findDuplicate(inputArray);
		//THEN
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
  public void findDuplicateWhenRegularNumbersThrowsExceptionTest() {
    //GIVEN
    int[] inputArray = {1,2,3};
    //WHEN
    underTest.findDuplicate(inputArray);
    //THEN
  }
	
	@Test(enabled = false)
	public void findDuplicateWhenNoDuplicationTest() {
		//GIVEN
		int[] inputArray = {1,3,4};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test(enabled = false)
	public void findDuplicateWhenNoMissingTest() {
		//GIVEN
		int[] inputArray = {1,2,3,3,4};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test(enabled = false)
	public void findDuplicateWhenMoreMissingTest() {
		//GIVEN
		int[] inputArray = {3,3,4};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test(enabled = false)
	public void findDuplicateWhenMoreDuplicationTest() {
		//GIVEN
		int[] inputArray = {2,2,3,3,4};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void findDuplicateWhenLengthOnlyTwoTest() {
		//GIVEN
		int[] inputArray = {2,2};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 2);
	}
	
	@Test
	public void findDuplicateWhenValid7ParamGivesValidResultTest() {
		//GIVEN
		int[] inputArray = {1,2,3,5,6,7,7,8};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 7);
	}
	
	@Test
	public void findDuplicateWhenValid4ParamGivesValidResultTest() {
		//GIVEN
		int[] inputArray = {1,2,3,4,4,5,6,8};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 4);
	}
	
	@Test
	public void findDuplicateWhenValid5ParamGivesValidResultTest() {
		//GIVEN
		int[] inputArray = {2,3,5,5};
		//WHEN
		int result = underTest.findDuplicate(inputArray);
		//THEN
		Assert.assertEquals(result, 5);
	}
}
