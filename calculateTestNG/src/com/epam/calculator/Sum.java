package com.epam.calculator;

public class Sum {

    public Integer calculate(String a, String b) {
        Integer intA, intB;
        intA = parseInt(a);
        intB = parseInt(b);
        return intA + intB;
    }

    private Integer parseInt(String input) {
        validateNotNull(input);
        return doParseInt(input);
    }

    private Integer doParseInt(String input) {
        Integer result = null;
        try {
            result = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Only int value is accepted!");
        }
        return result;
    }

    private void validateNotNull(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Null parameter is illegal!");
        }
    }
}
