package com.nearsoft.apprentice.tdd.examples

import org.junit.Test
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*
import java.lang.Void as should

/**
 * Created by jresendiz on 28/08/16.
 *
 Write a program that takes an integer argument and returns a List<Integer>.
 That list contains the prime factors in numerical sequence.

 e.g:

 2 -> [2]
 8 -> [2, 2, 2]
 10 -> [2, 5]
 20 -> [2, 2, 5]
 330 -> [2, 3, 5, 11]
 */
class PrimeFactorsTest extends GroovyTestCase {
    def generatePrimesUpTo(int upperLimit) {
        def primes = []
        def numbers = 2..upperLimit
        /* Quadratic complexity?? Seems to be higher ...  */
        for(number in numbers) {
            boolean isComposite = false
            for(prime in primes) {
                if(number % prime == 0) {
                    isComposite = true
                    break;
                }
            }
            if(!isComposite) {
                primes.add(number)
                continue;
            }
        }
        return primes
    }
}