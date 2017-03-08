def generatePrimesUpTo(int upperLimit) {
    def primes = []
    def numbers = 2..upperLimit
    for (number in numbers) {
        boolean isComposite = false
        for (prime in primes) {
            if (number % prime == 0) {
                isComposite = true
                break;
            }
        }
        if (!isComposite) {
            primes.add(number)
            continue;
        }
    }
    return primes
}
