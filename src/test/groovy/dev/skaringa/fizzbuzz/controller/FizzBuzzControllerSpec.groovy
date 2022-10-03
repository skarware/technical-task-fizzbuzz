package dev.skaringa.fizzbuzz.controller

import dev.skaringa.fizzbuzz.SpecBaseIT
import dev.skaringa.fizzbuzz.model.IntegerSequenceDataEntry
import dev.skaringa.fizzbuzz.model.Sequence
import dev.skaringa.fizzbuzz.model.SequenceType
import dev.skaringa.fizzbuzz.model.StringSequenceDataEntry
import dev.skaringa.fizzbuzz.service.FizzBuzzSequenceGenerator
import org.springframework.beans.factory.annotation.Autowired

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FizzBuzzControllerSpec extends SpecBaseIT {
    @Autowired
    private FizzBuzzSequenceGenerator fizzBuzzSequenceGenerator

    def "GET /api/sequences/fizz-buzz/{length} returns sequence"() {
        given:
        def length = 5

        and: "fizz buzz generator stub returns a sequence"
        def sequence = new Sequence<>(
                SequenceType.FIZZ_BUZZ,
                List.of(new IntegerSequenceDataEntry(1),
                        new IntegerSequenceDataEntry(2),
                        new StringSequenceDataEntry("Fizz"),
                        new IntegerSequenceDataEntry(4),
                        new StringSequenceDataEntry("Buzz")))
        1 * fizzBuzzSequenceGenerator.generate(length) >> sequence

        when: "GET /api/sequences/fizz-buzz/{length} is called"
        def response = mockMvc.perform(get("/api/sequences/fizz-buzz/{length}", length))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.type').value(SequenceType.FIZZ_BUZZ.name()))
                .andExpect(jsonPath('$.data', hasSize(sequence.data.size())))
                .andExpect(jsonPath('$.data').value(sequence.data.collect({ it.value })))
    }
}
