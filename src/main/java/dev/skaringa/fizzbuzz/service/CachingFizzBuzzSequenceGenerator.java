package dev.skaringa.fizzbuzz.service;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class CachingFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    private final FizzBuzzSequenceGenerator delegateGenerator;
    private List<Serializable> sequence = new ArrayList<>();

    public int getCachedSequenceSize() {
        return sequence.size();
    }

    @Override
    public List<Serializable> generate(int length) {
        boolean cachedSequenceIsNotEmpty = !sequence.isEmpty();
        boolean cachedSequenceIsAtLeastOfGivenLengthSize = sequence.size() >= length;

        if (cachedSequenceIsNotEmpty && cachedSequenceIsAtLeastOfGivenLengthSize) {
            return generate(1, length);
        } else if (cachedSequenceIsNotEmpty) {
            merge(delegateGenerator.generate(sequence.size() + 1, length));
        } else {
            sequence = delegateGenerator.generate(length);
        }

        return sequence;
    }

    @Override
    public List<Serializable> generate(int fromNumber, int toNumber) {
        if (sequence.size() < toNumber) {
            generate(toNumber);
        }

        return sequence.subList(fromNumber - 1, toNumber);
    }

    private void merge(List<Serializable> newSequenceEntries) {
        sequence = Stream.of(sequence, newSequenceEntries)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());
    }
}
