package com.nearsoft.apprentice.tdd.examples

import org.junit.Before
import org.junit.Test
import org.spockframework.compiler.model.SetupBlock

import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*
import java.lang.Void as should

/**
 * Created by jresendiz on 28/08/16.
 *
 Write a program that takes an integer argument and returns a List<Integer>.
 That list contains the prime factors in numerical sequence.

 Recuerda ... Si un número entero mayor que 1 no tiene divisores primos menores
 o iguales que su raíz, entonces es primo.

 26 ~= 5.1, 2,3,5

 e.g:

 2 -> [2]
 8 -> [2, 2, 2]
 10 -> [2, 5]
 20 -> [2, 2, 5]
 330 -> [2, 3, 5, 11]
 */
class PrimeFactorsTest extends GroovyTestCase {
    PrimeFactors primeFactors;

    @Test
    void testPrimeFactorsOfNumberTwo() {
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(2) == [2]
    }

    @Test
    void testPrimeFactorsOfNumberEight() { // OK
        // 8 -> [2, 2, 2]
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(8) == [2,2,2]
    }

    @Test
    void testPrimeFactorsOfTen() {
        // 10 -> [2, 5]
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(10) == [2,5]
    }
    @Test
    void testPrimeFactorsOfTwenty() {
        // 20 -> [2, 2, 5]
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(20) == [2,2,5]
    }

    @Test
    void testOtherNumber () {
        // 330 -> [2, 3, 5, 11]
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(330) == [2,3,5,11]
    }

    @Test
    void testAPrime () {
        // 330 -> [2, 3, 5, 11]
        primeFactors = new PrimeFactors();
        assert primeFactors.getPrimeFactors(17) == [17]
    }
}