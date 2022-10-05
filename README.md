# technical-task-fizzbuzz

Yet another technical task I got while looking for a new project.

## About

##### Web API that generates FizzBuzz sequence.

Application made using <i><b>Java 11, Spring Boot v2.7.3, Spring Web, Swagger, Spock + Groovy, Lombok and Maven</b></i>.

## Reasoning on design decisions

### Endpoint to Service

One could create `SequanceControler/Service` but `FizzBuzzController/Service` makes classes less likely to change for
other reasons.

Added Sequence response model and enum for sequence type to accommodate addition of other type sequence. Factories
create response model from `List<Serializable>` data generated by generators. Factories are needed because generators
should not know about response models. Factories are single place for data conversions, also helps to hide data
structures used from Service class. In case generated data or response model changes, Service won't change.

### FizzBuzz sequence generators

Was trying few implementations of FizzBuzz sequence generator, one imperative other in functional programming paradigm.

`SimpleFizzBuzzSequenceGenerator` uses imperative for loop, using it my focus was on performance first, readability
second.
`ArrayList` chosen for efficiency, when capacity is known, for add and get element operations time complexity is O(1).

`StreamingFizzBuzzSequenceGenerator` takes more functional programming approach, is easy to read and understand, also
safer because uses fewer assignments to variables so less prone to side effects.

Both generators use `FizzBuzzWordResolver` to get Fizz-Buzz phrases for a number. Its modulo operations optimized.
Thanks to compiler "Fizz" and "Buzz" static strings concatenation should be as fast as having single "FizzBuzz" phrase.

`SimpleFizzBuzzSequenceGenerator` is `@Primary` and roughly 40% faster than streaming impl.

Primary generator is configurable in `FizzBuzzSequenceGeneratorConfig` class.

### Shortcomings

After adding Generators which returns List of Serializable items I have doubt in need for different types
of `SequenceDataEntry`. `Sequence.data` might also be of Serializable type.

Caching generators would be nice (WIP)

## How to set up the application

Open terminal and use git clone command to download the remote GitHub repository to your computer:

```
git clone https://github.com/skarware/technical-task-fizzbuzz.git
```

It will create a new folder with same name as GitHub repository "technical-task-fizzbuzz". All the project files and git
data will be cloned into it. After cloning complete change directories into that new folder:

```
cd technical-task-fizzbuzz
```

Give a `mvnw` file execution permission (or expect error: `./mvnw: command not found`)

```
chmod +x mvnw
```

## How to use

To launch the application run this command (uses maven wrapper):

```
./mvnw clean spring-boot:run
```

Or using your installed maven version:

```
mvn clean spring-boot:run
```

## Swagger UI

For interacting with application API one can use a browser to reach <b><i>Swagger UI</i></b>, if you run app on a local
machine, by hitting URL: http://localhost:18080/swagger-ui/

###

Doesn't work? well, it works on my machine...