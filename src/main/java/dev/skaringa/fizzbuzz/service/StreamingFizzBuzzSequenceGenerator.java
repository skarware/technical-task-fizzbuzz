package dev.skaringa.fizzbuzz.service;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StreamingFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    @Override
    public List<Serializable> generate(int length) {
        return IntStream.rangeClosed(1, length)
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
