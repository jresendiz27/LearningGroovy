package com.nearsoft.apprentice.tdd.examples

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*

/**
 * Created by jresendiz on 28/08/16.

Write a program that prints the numbers from 1 to 100.
But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz".
For numbers which are multiples of both three and five print "FizzBuzz".

Sample output:

1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
16
17
Fizz
19
Buzz
... etc up to 100

* */
class FizzBuzzTest extends GroovyTestCase {
    FizzBuzz fizzBuzz = new FizzBuzz()
    @Test
    void testFizzOnNumberMultipleOfThree () {
        assertThat(fizzBuzz.validate(3), equalTo("Fizz"));
    }
    @Test
    void testBuzzOnNumberMultipleOfFive() {
        assertThat(fizzBuzz.validate(5), equalTo("Buzz"))
    }
    @Test
    void testOtherNumber() {
        assertThat(fizzBuzz.validate(2), equalTo("2"))
    }
    @Test
    void testSampleOutput() {
        String sampleOutput = """1
2
Fizz
4
Buzz
Fizz
7
8
Fizz
Buzz
11
Fizz
13
14
FizzBuzz
16
17
Fizz
19
Buzz
""" // DON'T DO THIS
        assert sampleOutput.equals(fizzBuzz.generateOutput(20))
    }
}