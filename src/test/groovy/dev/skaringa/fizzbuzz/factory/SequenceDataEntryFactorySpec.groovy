package dev.skaringa.fizzbuzz.factory

import spock.lang.Specification

class SequenceDataEntryFactorySpec extends Specification {
    private def factory = new SequenceDataEntryFactory()

    def "creates integer sequence data entry model correctly"() {
        given:
        def dataEntries = [1, 2,]

        when:
        def result = factory.toModels(dataEntries)

        then:
        result.size() == 2
        result.collect({ it.value }) == dataEntries
    }

    def "creates string sequence data entry model correctly"() {
        given:
        def dataEntries = ["Fizz", "Buzz"]

        when:
        def result = factory.toModels(dataEntries)

        then:
        result.size() == 2
        result.collect({ it.value }) == dataEntries
    }

    def "creates mixed sequence data entry model correctly"() {
        given:
        def dataEntries = [1, 2, "Fizz"]

        when:
        def result = factory.toModels(dataEntries)

        then:
        result.size() == 3
        result.collect({ it.value }) == dataEntries
    }


    def "throws exception when unexpected sequence data entry type given"() {
        given: "from date and period"
        def unexpectedType = 1.2 as Double

        when:
        factory.toModels([unexpectedType])


        then: "Exception is thrown"
        def ex = thrown(IllegalArgumentException)
        ex.message == "Unexpected type of sequence data entry"
    }
}
