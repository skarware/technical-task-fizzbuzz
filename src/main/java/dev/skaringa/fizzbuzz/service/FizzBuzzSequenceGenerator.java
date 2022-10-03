package dev.skaringa.fizzbuzz.service;

import dev.skaringa.fizzbuzz.model.Sequence;
import dev.skaringa.fizzbuzz.model.SequenceDataEntry;

public interface FizzBuzzSequenceGenerator {
    Sequence<SequenceDataEntry> generate(int length);
}
