package com.nearsoft.apprentice.tdd.examples

/**
 * Created by jresendiz on 28/08/16.
 */
class FizzBuzz {
    String validate(Integer number) {
        if(number % 3 == 0 && number % 5 == 0) {
            "FizzBuzz"
        } else if(number % 3 == 0) {
            "Fizz"
        } else if(number % 5 == 0) {
            "Buzz"
        } else {
            "$number"
        }
    }

    String generateOutput(Integer i) {
        def numbers = 1..i // generates a range from 1 to i
        def output = ""
        for(number in numbers) { //for loop, the groovy way
            output+= this.validate(number) + "\n"
        }
        return output
    }
}
