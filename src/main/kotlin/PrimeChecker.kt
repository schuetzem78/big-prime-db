import java.math.BigInteger

class PrimeChecker {

    companion object {
        fun isPrime(n: BigInteger, knownPrimes: List<BigInteger>): Boolean {

            if (n < BigInteger.TWO) return false

            if (knownPrimes.isEmpty() || knownPrimes.last().multiply(knownPrimes.last()) < n) {
                println("Liste der bekannten Primzahlen ist zu klein!")
                throw IllegalArgumentException("Liste der bekannten Primzahlen ist zu klein!")
            }

            for (p in knownPrimes) {
                if (p.multiply(p) > n) break
                if (n.mod(p) == BigInteger.ZERO) return false
            }

            return true
        }
    }
}
