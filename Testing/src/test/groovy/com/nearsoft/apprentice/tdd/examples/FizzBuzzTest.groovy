package com.nearsoft.apprentice.tdd.examples

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*
import java.lang.Void as should

/**
 * Created by jresendiz on 28/08/16.

Write a program that prints the numbers from 1 to 100.
But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz".
For numbers which are multiples of both three and five print "FizzBuzz?".

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
    should "Test 'Fizz' string response when number is multiple of 3" () {
        assertThat(fizzBuzz.generate(3), equalTo("Fizz"));
    }
    @Test
    should "Test 'Buzz' string response when number is multiple of 5"() {
        assertThat(fizzBuzz.generate(5), equalTo("Buzz"))
    }
    @Test
    should "Test 'FizzBuzz' string response when number is multiple of 3 and 5" () {
        assertThat(fizzBuzz.generate(15), equalTo("FizzBuzz"))
    }
}