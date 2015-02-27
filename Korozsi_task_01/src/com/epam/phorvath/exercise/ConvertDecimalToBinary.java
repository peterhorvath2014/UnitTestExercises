package com.epam.phorvath.exercise;
//Task:
//    Convert decimal to to binary
//
//Description:
//    Write a program to convert not negative decimal number to binary format using numeric operations.
//
//Interface to be implemented:
//    public String getBinaryFormat(int number)
//
//Examples:
//1)
//    Input: 0
//    Result: 0
// 
//2) 
//    Input: 1
//    Result: 1
// 
//3) 
//    Input: 2
//    Result: 10
// 
//4) 
//    Input: 9
//    Result: 1001
public class ConvertDecimalToBinary {
	 public String getBinaryFormat(int number) {
		 if (this.isParameterValid(number)) {
			 throw new IllegalArgumentException();
		 }
		 StringBuffer result = new StringBuffer();
		 if (number == 0) {
			 result.append(0);
		 } else {
			 while (number > 0) {
				 if ((number % 2) == 1) {
					 result = result.insert(0, '1');
					 number = number - 1;
				 } else {
					 result = result.insert(0, '0');
				 }
				 number = number / 2;
		 	}
		 }
		 return result.toString();
	 }

	private boolean isParameterValid(int number) {
		boolean result = false;
		if (number<0) {
			result = true;
		}
		return result;
	}
}
