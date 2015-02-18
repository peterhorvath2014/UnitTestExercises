package com.epam.exercise3;

public class IntegerStack {
	private Integer[] stack = new Integer[0];

	public Integer pop() {
		checkStackIfEmpty();
		final int lastItemPos = stack.length - 1;
		final int newStackLength = stack.length - 1;

		Integer result = stack[lastItemPos];

		final Integer[] newStack = copyStackToNewStack(newStackLength,
				newStackLength);

		stack = newStack;
		return result;
	}

	public void push(Integer number) {
		validateNotNull(number);
		final int stackLength = stack.length;
		final int newStackLength = stackLength + 1;
		final int newValuePos = newStackLength - 1;

		final Integer[] newStack = copyStackToNewStack(stackLength,
				newStackLength);

		newStack[newValuePos] = number;
		stack = newStack;
	}

	public boolean isEmpty() {
		return stack.length == 0;
	}

	public int count() {
		return stack.length;
	}
	
	private Integer[] copyStackToNewStack(final int stackLength,
			final int newStackLength) {
		final Integer[] newStack = new Integer[newStackLength];

		for (int i = 0; i < stackLength; i++) {
			newStack[i] = stack[i];
		}
		return newStack;
	}

	private void validateNotNull(Integer number) {
		if (number == null) {
			throw new IllegalArgumentException();
		}
	}

	private void checkStackIfEmpty() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
	}
}
