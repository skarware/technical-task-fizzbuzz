package dev.skaringa.fizzbuzz.service;

import dev.skaringa.fizzbuzz.factory.FizzBuzzSequenceFactory;
import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FizzBuzzService {
    private final FizzBuzzSequenceGenerator fizzBuzzSequenceGenerator;
    private final FizzBuzzSequenceFactory fizzBuzzSequenceFactory;

    public Sequence<SequenceDataEntry> getSequence(int length) {
        return fizzBuzzSequenceFactory.toModel(fizzBuzzSequenceGenerator.generate(length));
    }
}
