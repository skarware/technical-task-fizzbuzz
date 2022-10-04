package dev.skaringa.fizzbuzz.controller

import dev.skaringa.fizzbuzz.SpecBaseIT
import dev.skaringa.fizzbuzz.model.SequenceType
import dev.skaringa.fizzbuzz.service.FizzBuzzSequenceGenerator
import org.springframework.beans.factory.annotation.Autowired

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class FizzBuzzControllerSpec extends SpecBaseIT {
    @Autowired
    private FizzBuzzSequenceGenerator fizzBuzzSequenceGenerator

    def "GET /api/sequences/fizz-buzz/{length} returns sequence"() {
        given:
        def length = 5
        def sequence = [1, 2, "Fizz", 4, "Buzz"]

        and: "fizz buzz generator stub returns a sequence"
        1 * fizzBuzzSequenceGenerator.generate(length) >> sequence

        when: "GET /api/sequences/fizz-buzz/{length} is called"
        def response = mockMvc.perform(get("/api/sequences/fizz-buzz/{length}", length))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.type').value(SequenceType.FIZZ_BUZZ.name()))
                .andExpect(jsonPath('$.data').value(sequence))
    }
}
