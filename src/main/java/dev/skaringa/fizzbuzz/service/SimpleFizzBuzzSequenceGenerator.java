package dev.skaringa.fizzbuzz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    @Override
    public List<Serializable> generate(int length) {
        return generate(1, length);
    }

    @Override
    public List<Serializable> generate(int fromNumber, int toNumber) {
        List<Serializable> sequence = new ArrayList<>(toNumber);

        for (int number = fromNumber; number <= toNumber; number++) {
            String word = FizzBuzzWordResolver.resolve(number);
            appendOneOf(sequence, number, word);
        }

        return sequence;
    }

    private void appendOneOf(List<Serializable> sequence, int number, String word) {
        if (word.isBlank()) {
            sequence.add(number);
        } else {
            sequence.add(word);
        }
    }
}
