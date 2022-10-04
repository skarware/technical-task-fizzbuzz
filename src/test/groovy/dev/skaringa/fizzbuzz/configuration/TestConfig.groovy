package dev.skaringa.fizzbuzz.configuration

import dev.skaringa.fizzbuzz.service.FizzBuzzSequenceGenerator
import dev.skaringa.fizzbuzz.service.SimpleFizzBuzzSequenceGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import spock.mock.DetachedMockFactory

@Profile("test")
@Configuration
class TestConfig {
    private static final def DETACHED_MOCK_FACTORY = new DetachedMockFactory()

    @Bean
    @Primary
    FizzBuzzSequenceGenerator fizzBuzzSequenceGeneratorSpy() {
        return DETACHED_MOCK_FACTORY.Spy(SimpleFizzBuzzSequenceGenerator)
    }
}
