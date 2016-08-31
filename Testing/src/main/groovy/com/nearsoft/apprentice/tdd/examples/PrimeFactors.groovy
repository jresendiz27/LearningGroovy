package com.nearsoft.apprentice.tdd.examples

/**
 * Created by jresendiz on 28/08/16.
 */
class PrimeFactors {
    List<Integer> getPrimeFactors(Integer number) {
        List <Integer> primeFactors = [] // [2,]
        List <Integer> primes = this.generatePrimesUpTo(number) // 2,3,5,7 ... upto number
        boolean flag = true;
        for(int i=0; i < primes.size() && flag;) { //
            if(number % primes[i] == 0) {
                primeFactors.add(primes[i]) // 2 es factor primo
                number/= primes[i] //4
                i = 0
            } else {
                i++
                continue;
            }
            flag = (number > 1)
        }

        return primeFactors
    }

    def generatePrimesUpTo(int upperLimit) {
        def primes = []
        def numbers = 2..upperLimit
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
