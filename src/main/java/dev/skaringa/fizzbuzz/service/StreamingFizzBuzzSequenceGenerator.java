package dev.skaringa.fizzbuzz.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamingFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    @Override
    public List<Serializable> generate(int length) {
        return generate(1, length);
    }

    @Override
    public List<Serializable> generate(int fromNumber, int toNumber) {
        return IntStream.rangeClosed(fromNumber, toNumber)
                .mapToObj(this::getFizzBuzzOrNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    private Serializable getFizzBuzzOrNumber(int number) {
        String word = FizzBuzzWordResolver.resolve(number);

        if (word.isBlank()) {
            return number;
        } else {
            return word;
        }
    }
}
