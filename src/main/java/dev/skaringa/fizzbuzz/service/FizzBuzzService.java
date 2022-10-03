package dev.skaringa.fizzbuzz.service;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FizzBuzzService {
    private final FizzBuzzSequenceGenerator fizzBuzzSequenceGenerator;

    public Sequence<SequenceDataEntry> getSequence(int length) {
        return fizzBuzzSequenceGenerator.generate(length);
    }
}
