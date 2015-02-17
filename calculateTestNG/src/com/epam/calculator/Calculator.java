package com.epam.calculator;


public class Calculator {
    private Logger log;
    private Sum sum;

    public Integer sum(String input, String separator) {
        log.logInfo("Calculation started");
        validateInput(input);
        String[] numbers = parseInput(input, separator);
        Integer result = doCalculate(numbers);
        log.logInfo("Calculation ended! Result is " + result + "!");
        return result;
    }

    private Integer doCalculate(String[] numbers) {
        Integer result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result = sum.calculate(result.toString(), numbers[i]);
        }
        return result;
    }

    private String[] parseInput(String input, String separator) {
        String[] numbers = input.split(separator);
        return numbers;
    }

    private void validateInput(String input) {
        validateNotNull(input);
        validateLenght(input);
    }

    private void validateLenght(String input) {
        if (input.length() == 0) {
            throw new IllegalArgumentException(
                    "Input must be at least 1 character length!");
        }
    }

    private void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null value is illegal!");
        }
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public void setSum(Sum sum) {
        this.sum = sum;
    }

}
