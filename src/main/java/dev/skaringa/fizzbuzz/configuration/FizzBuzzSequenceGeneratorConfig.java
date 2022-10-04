package dev.skaringa.fizzbuzz.configuration;

import dev.skaringa.fizzbuzz.service.FizzBuzzSequenceGenerator;
import dev.skaringa.fizzbuzz.service.SimpleFizzBuzzSequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Profile("!test")
@Configuration
public class FizzBuzzSequenceGeneratorConfig {
    @Bean
    @Primary
    public FizzBuzzSequenceGenerator simpleFizzBuzzSequenceGenerator() {
        return new SimpleFizzBuzzSequenceGenerator();
    }
}
