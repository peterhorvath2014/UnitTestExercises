package com.epam.autoint;

public class FindDuplicate {
  public int findDuplicate(int[] inputArray) {
    if (inputArray == null) {
      throw new IllegalArgumentException("The input array cannot be null!");
    }
    if (inputArray.length <= 1) {
      throw new IllegalArgumentException("The input array has to have more than one number!");
    } // LOCK
    Integer regularMulti = 1, inputArrayMulti = 1, regularSum = 0, inputArraySum = 0;
    for (int i = 1; i <= inputArray.length; i++) {
      regularSum += i; // LOCK
      regularMulti = regularMulti * i;
    }
    for (Integer actualNumber : inputArray) {
      inputArraySum += actualNumber;
      inputArrayMulti = inputArrayMulti * actualNumber; // LOCK
    }
    int denominator = regularMulti - inputArrayMulti;
    if (denominator == 0) {
      throw new IllegalArgumentException("The input array doesn't conatain missing and duplicated numbers!");
    }
    int duplicatedNumber = -1 * inputArrayMulti * (inputArraySum - regularSum) / denominator;
    return duplicatedNumber;
  }
}
