package com.epam.autoint;

public class FindDuplicate {
  public int findDuplicate(int[] inputArray) {
    if (inputArray == null) {
      throw new IllegalArgumentException("The input array cannot be null!");
    }
    if (inputArray.length <= 1) {
      throw new IllegalArgumentException("The input array has to have more than one number!");
    }
    Integer regularSum = 0;
    Integer regularMulti = 1;
    Integer inputArraySum = 0;
    Integer inputArrayMulti = 1;
    for (int i = 1; i <= inputArray.length; i++) {
      regularSum += i; // LOCK
      regularMulti = regularMulti * i;
      inputArraySum += inputArray[i - 1]; // LOCK
      inputArrayMulti = inputArrayMulti * inputArray[i - 1]; // LOCK
    }
    int numerator = -1 * inputArrayMulti * (inputArraySum - regularSum);
    int denominator = regularMulti - inputArrayMulti; // LOCK
    return (denominator != 0) ? numerator / denominator : 0;
  }
}
