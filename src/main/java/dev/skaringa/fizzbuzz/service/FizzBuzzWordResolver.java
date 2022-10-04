package dev.skaringa.fizzbuzz.service;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FizzBuzzWordResolver {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    static String resolve(int number) {
        boolean isFizzNumber = number % 3 == 0;
        boolean isBuzzNumber = number % 5 == 0;

        String word = "";
        if (isFizzNumber) word += FIZZ;
        if (isBuzzNumber) word += BUZZ;

        return word;
    }
}