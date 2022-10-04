package dev.skaringa.fizzbuzz.service;

import java.io.Serializable;
import java.util.List;

public interface FizzBuzzSequenceGenerator {
    List<Serializable> generate(int length);
}
