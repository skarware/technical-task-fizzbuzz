package dev.skaringa.fizzbuzz.factory

import dev.skaringa.fizzbuzz.SpecBase
import dev.skaringa.fizzbuzz.model.SequenceType

class FizzBuzzSequenceFactoryFactorySpec extends SpecBase {
    private def factory = new FizzBuzzSequenceFactory(new SequenceDataEntryFactory())

    def "creates fizz-buzz sequence model correctly"() {
        given:
        def dataEntries = [1, 2, "Fizz", 4, "Buzz"]

        when:
        def result = factory.toModel(dataEntries)

        then:
        result.type == SequenceType.FIZZ_BUZZ
        result.data.collect({ it.value }) == dataEntries
    }
}
