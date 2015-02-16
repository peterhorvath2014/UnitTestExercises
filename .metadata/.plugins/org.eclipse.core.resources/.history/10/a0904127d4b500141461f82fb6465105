package com.epam.junit;


public class BasicJUnitExample {
    private static final Integer THE_ULTIMATE_ANSWER = Integer.valueOf(42);

    public Integer ultimateAnswer() {
        return THE_ULTIMATE_ANSWER;
    }

    public Integer badAnswer(Integer input) {
        if(THE_ULTIMATE_ANSWER.equals(input)){
            throw new IllegalArgumentException("Hey, you wanted a bad answer right?!");
        }
        return input;
    }

    public boolean isTheUltimateAnswer(Integer input) {
        return THE_ULTIMATE_ANSWER.equals(input);
    }
}
