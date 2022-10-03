package dev.skaringa.fizzbuzz.service;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyFizzBuzzSequenceGenerator implements FizzBuzzSequenceGenerator {
    @Override
    public Sequence<SequenceDataEntry> generate(int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
