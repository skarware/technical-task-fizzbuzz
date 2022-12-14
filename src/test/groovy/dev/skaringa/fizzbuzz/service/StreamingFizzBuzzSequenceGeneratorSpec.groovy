package dev.skaringa.fizzbuzz.service

import dev.skaringa.fizzbuzz.SpecBase

class StreamingFizzBuzzSequenceGeneratorSpec extends SpecBase {
    private def streamingFizzBuzzSequenceGenerator = new StreamingFizzBuzzSequenceGenerator()

    def "returns correct sequence when from and to numbers given"() {
        given:
        def fromNumber = 6
        def toNumber = 15

        when:
        def result = streamingFizzBuzzSequenceGenerator.generate(fromNumber, toNumber)

        then:
        result.size() == 10
        result == ["Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz"]
    }

    def "returns correct sequence when length of 5 given"() {
        given:
        def length = 5

        when:
        def result = streamingFizzBuzzSequenceGenerator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz"]
    }

    def "returns correct sequence when length of 15 given"() {
        given:
        def length = 15

        when:
        def result = streamingFizzBuzzSequenceGenerator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz", "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz"]
    }

    def "returns correct sequence when length of 100 given"() {
        given:
        def length = 100

        when:
        def result = streamingFizzBuzzSequenceGenerator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz", "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz", 16, 17, "Fizz", 19, "Buzz", "Fizz", 22, 23, "Fizz", "Buzz", 26, "Fizz", 28, 29, "FizzBuzz", 31, 32, "Fizz", 34, "Buzz", "Fizz", 37, 38, "Fizz", "Buzz", 41, "Fizz", 43, 44, "FizzBuzz", 46, 47, "Fizz", 49, "Buzz", "Fizz", 52, 53, "Fizz", "Buzz", 56, "Fizz", 58, 59, "FizzBuzz", 61, 62, "Fizz", 64, "Buzz", "Fizz", 67, 68, "Fizz", "Buzz", 71, "Fizz", 73, 74, "FizzBuzz", 76, 77, "Fizz", 79, "Buzz", "Fizz", 82, 83, "Fizz", "Buzz", 86, "Fizz", 88, 89, "FizzBuzz", 91, 92, "Fizz", 94, "Buzz", "Fizz", 97, 98, "Fizz", "Buzz"]
    }

    def "returns correct sequence length of 1E7"() {
        given:
        def length = 10000000

        when:
        def result = streamingFizzBuzzSequenceGenerator.generate(length)

        then:
        result.size() == length
    }
}
