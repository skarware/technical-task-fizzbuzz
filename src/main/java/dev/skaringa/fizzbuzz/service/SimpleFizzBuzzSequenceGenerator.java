package dev.skaringa.fizzbuzz.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    @Override
    public List<Serializable> generate(int length) {
        List<Serializable> sequence = new ArrayList<>(length);

        for (int number = 1; number <= length; number++) {
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
