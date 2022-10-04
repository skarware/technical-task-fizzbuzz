package dev.skaringa.fizzbuzz.controller

import dev.skaringa.fizzbuzz.SpecBaseIT
import dev.skaringa.fizzbuzz.api.ErrorCode
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

    def "GET /api/sequences/fizz-buzz/{length} returns 400 when length greater than 10000 given"() {
        given:
        def length = 10001

        when: "GET /api/sequences/fizz-buzz/{length} is called"
        def response = mockMvc.perform(get("/api/sequences/fizz-buzz/{length}", length))

        then: "response status is 400"
        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.[0].code').value(ErrorCode.ILLEGAL_ARGUMENT.name()))
                .andExpect(jsonPath('$.[0].message').value("getFizzBuzz.length: must be less than or equal to 10000"))

        and: "fizz buzz generator stub is not called"
        0 * fizzBuzzSequenceGenerator.generate(_)
    }

    def "GET /api/sequences/fizz-buzz/{length} returns 400 when length less than 1 given"() {
        given:
        def length = 0

        when: "GET /api/sequences/fizz-buzz/{length} is called"
        def response = mockMvc.perform(get("/api/sequences/fizz-buzz/{length}", length))

        then: "response status is 400"
        response
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath('$.[0].code').value(ErrorCode.ILLEGAL_ARGUMENT.name()))
                .andExpect(jsonPath('$.[0].message').value("getFizzBuzz.length: must be greater than or equal to 1"))

        and: "fizz buzz generator stub is not called"
        0 * fizzBuzzSequenceGenerator.generate(_)
    }

    def "GET /api/sequences/fizz-buzz/{length} returns 400 when invalid length type given"() {
        given:
        def length = "abc"

        when: "GET /api/sequences/fizz-buzz/{length} is called"
        def response = mockMvc.perform(get("/api/sequences/fizz-buzz/{length}", length))

        then: "response status is 400"
        response
                .andExpect(status().isBadRequest())

        and: "fizz buzz generator stub is not called"
        0 * fizzBuzzSequenceGenerator.generate(_)
    }
}
