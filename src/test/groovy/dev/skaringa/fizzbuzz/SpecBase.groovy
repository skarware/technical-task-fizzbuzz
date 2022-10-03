package dev.skaringa.fizzbuzz


import spock.lang.Specification

abstract class SpecBase extends Specification {
    private static final def RANDOM = new Random()

    protected static def randomId() {
        return RANDOM.nextLong()
    }
}
