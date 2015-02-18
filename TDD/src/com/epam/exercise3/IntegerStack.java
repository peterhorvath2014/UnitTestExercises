package com.epam.exercise3;

public class IntegerStack {
	private Integer[] stack = new Integer[0];

	public void push(Integer number) {
		validateNotNull(number);
		final int stackLength = stack.length;
		final int newStackLength = stackLength + 1;
		final int newValuePos =  newStackLength - 1;

		final Integer[] newStack = new Integer[newStackLength];

		for (int i = 0; i < stackLength; i++) {
			newStack[i] = stack[i];
		}
		newStack[newValuePos] = number;
		stack = newStack;
	}

	private void validateNotNull(Integer number) {
		if (number == null) {
			throw new IllegalArgumentException();
		}
	}

	public Integer pop() {
		final int lastItemPos = stack.length - 1;
		final int newStackLength = stack.length - 1;
		
		Integer result = stack[lastItemPos];
		final Integer[] newStack = new Integer[newStackLength];
		
		for (int i = 0; i < newStackLength; i++) {
			newStack[i] = stack[i];
		}
		stack = newStack;
		return result;
	}

	public int count() {
		return stack.length;
	}
}
