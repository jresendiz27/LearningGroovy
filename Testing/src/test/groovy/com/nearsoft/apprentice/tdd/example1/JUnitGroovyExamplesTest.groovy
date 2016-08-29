package com.nearsoft.apprentice.tdd.example1

import org.junit.After
import org.junit.Before
import org.junit.Test
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*
import static org.junit.matchers.JUnitMatchers.*

/**
 * Created by jresendiz on 28/08/16.
 */
class JUnitGroovyExamplesTest extends GroovyTestCase {
    @Before
    def setup() {
        println "Something that should be executed before each test case"
    }

    @Test
    void testAssertion() {
        assert true
    }

    @Test
    void testThisShouldFail() {
        def element = [1, 2, 3, 4]
        shouldFail {
            element.get(4);
        }
    }

    void testThatIsNotUsingTestAnnotation() {
        assert true
    }

    void testUsingHamcrest() {
        def values = ["one", "two", "three", "four"]
        assertThat(values[1], is(not("one")))
        assertThat("The values variable has four", values, hasItem("four"))
    }

    @After
    def cleanup() {
        println "Something that should be executed after each test case"
    }
}
