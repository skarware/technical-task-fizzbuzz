package dev.skaringa.fizzbuzz.service

import dev.skaringa.fizzbuzz.SpecBaseIT

class CachingFizzBuzzSequenceGeneratorSpec extends SpecBaseIT {
    private def delegateGenerator = Spy(SimpleFizzBuzzSequenceGenerator)
    private def generator = new CachingFizzBuzzSequenceGenerator(delegateGenerator)

    def "returns correct sequence when from and to numbers given"() {
        given:
        def fromNumber = 6
        def toNumber = 15

        when:
        def result = generator.generate(fromNumber, toNumber)

        then:
        result.size() == 10
        result == ["Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz"]
    }

    def "returns correct sequence when length of 5 given"() {
        given:
        def length = 5

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz"]
    }

    def "returns correct sequence when length of 15 given"() {
        given:
        def length = 15

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz", "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz"]
    }

    def "returns correct sequence when length of 100 given"() {
        given:
        def length = 100

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        result == [1, 2, "Fizz", 4, "Buzz", "Fizz", 7, 8, "Fizz", "Buzz", 11, "Fizz", 13, 14, "FizzBuzz", 16, 17, "Fizz", 19, "Buzz", "Fizz", 22, 23, "Fizz", "Buzz", 26, "Fizz", 28, 29, "FizzBuzz", 31, 32, "Fizz", 34, "Buzz", "Fizz", 37, 38, "Fizz", "Buzz", 41, "Fizz", 43, 44, "FizzBuzz", 46, 47, "Fizz", 49, "Buzz", "Fizz", 52, 53, "Fizz", "Buzz", 56, "Fizz", 58, 59, "FizzBuzz", 61, 62, "Fizz", 64, "Buzz", "Fizz", 67, 68, "Fizz", "Buzz", 71, "Fizz", 73, 74, "FizzBuzz", 76, 77, "Fizz", 79, "Buzz", "Fizz", 82, 83, "Fizz", "Buzz", 86, "Fizz", 88, 89, "FizzBuzz", 91, 92, "Fizz", 94, "Buzz", "Fizz", 97, 98, "Fizz", "Buzz"]
    }

    def "returns correct sequence length of 1E7"() {
        given:
        def length = 10000000

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        generator.cachedSequenceSize == length
    }

    def "caching generator caches sequence on first call"() {
        given:
        def length = 15

        and:
        generator.cachedSequenceSize == 0

        when:
        def result = generator.generate(length)

        then: "sequence is cached"
        result.size() == length
        generator.cachedSequenceSize == length

        and: "delegate generator is called once"
        1 * delegateGenerator.generate(_, _)
    }

    def "caching generator caches sequence on first call when from and to numbers given"() {
        given:
        def fromNumber = 6
        def toNumber = 15

        when:
        def result = generator.generate(fromNumber, toNumber)

        then: "sequence is cached"
        result.size() == 10
        generator.cachedSequenceSize == toNumber

        and: "delegate generator is called once"
        1 * delegateGenerator.generate(_, _)
    }

    def "calls delegate generator only once when sequence is partially cached"() {
        given:
        def length = 15
        def cachedCapacity = 10

        and: "sequence is partially cached"
        generator.generate(cachedCapacity)

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        generator.cachedSequenceSize == length

        and: "delegate generator is called once"
        1 * delegateGenerator.generate(_, _)
    }

    def "calls delegate generator only once when sequence is partially cached and from and to numbers given"() {
        given:
        def fromNumber = 6
        def toNumber = 15
        def cachedCapacity = 10

        and: "sequence is partially cached"
        generator.generate(cachedCapacity)

        when:
        def result = generator.generate(fromNumber, toNumber)

        then:
        result.size() == 10
        generator.cachedSequenceSize == toNumber

        and: "delegate generator is called once"
        1 * delegateGenerator.generate(_, _)
    }

    def "does not call delegate generator when sequence is cached"() {
        given:
        def length = 15

        and: "sequence is cached"
        generator.generate(length)

        when:
        def result = generator.generate(length)

        then:
        result.size() == length
        generator.cachedSequenceSize == length

        and: "delegate generator is not called"
        0 * delegateGenerator.generate(_, _)
    }

    def "does not call delegate generator when sequence is cached and from and to numbers given"() {
        given:
        def fromNumber = 6
        def toNumber = 15

        and: "sequence is cached"
        generator.generate(toNumber)

        when:
        def result = generator.generate(fromNumber, toNumber)

        then:
        result.size() == 10
        generator.cachedSequenceSize == toNumber

        and: "delegate generator is not called"
        0 * delegateGenerator.generate(_, _)
    }
}
