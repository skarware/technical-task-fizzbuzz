package dev.skaringa.fizzbuzz.configuration

import dev.skaringa.fizzbuzz.service.FizzBuzzSequenceGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import spock.mock.DetachedMockFactory

@Configuration
@Profile("test")
class TestConfig {
    private static final def DETACHED_MOCK_FACTORY = new DetachedMockFactory()

    @Bean
    @Primary
    FizzBuzzSequenceGenerator fizzBuzzSequenceGeneratorSpy(FizzBuzzSequenceGenerator fizzBuzzSequenceGenerator) {
        return DETACHED_MOCK_FACTORY.Spy(fizzBuzzSequenceGenerator)
    }
}
