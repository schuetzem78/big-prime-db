import java.io.*
import java.math.BigInteger
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

private const val NUMBERS_TO_FIND = 1_000_000

fun main(args: Array<String>) {

    var primesPathName = "D:\\Primes"

    if (args.isNotEmpty()) {
        primesPathName = args[0]
    }

    val primesPath = File(primesPathName)
    val lastPrimeFileName = getLastPrimFile(primesPathName)
    val lastPrimeFile = File(lastPrimeFileName)
    val lastLine = readLastLine(lastPrimeFile)
    val lastPrime = BigInteger(lastLine!!)

    val knownPrimes = readKnownPrimes(primesPath)

    var numberToCheck = lastPrime.add(BigInteger.TWO)

    val foundPrimes_1 = ArrayList<String>()
    val foundPrimes_3 = ArrayList<String>()
    val foundPrimes_7 = ArrayList<String>()
    val foundPrimes_9 = ArrayList<String>()
    val foundPrimes_11 = ArrayList<String>()
    val foundPrimes_13 = ArrayList<String>()
    val foundPrimes_17 = ArrayList<String>()
    val foundPrimes_19 = ArrayList<String>()

    val outputFile_1 = primesPath.resolve("1.txt")
    val outputFile_3 = primesPath.resolve("3.txt")
    val outputFile_7 = primesPath.resolve("7.txt")
    val outputFile_9 = primesPath.resolve("9.txt")
    val outputFile_11 = primesPath.resolve("11.txt")
    val outputFile_13 = primesPath.resolve("13.txt")
    val outputFile_17 = primesPath.resolve("17.txt")
    val outputFile_19 = primesPath.resolve("19.txt")

    val sw = StopWatch()
    sw.start()

    val TWENTY = BigInteger.valueOf(20)

    if (numberToCheck.toString().endsWith("5")) {
        numberToCheck = numberToCheck.add(BigInteger.TWO)
    }

    val threads = ArrayList<Thread>()

    if (numberToCheck.toString().endsWith("1")) {
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_1, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_3, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_7, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_9, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_11, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_13, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_17, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_19, TWENTY))
    }

    if (numberToCheck.toString().endsWith("3")) {
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_3, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_7, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_9, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_11, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_13, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_17, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_19, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_1, TWENTY))
    }

    if (numberToCheck.toString().endsWith("7")) {
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_7, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_9, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_11, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_13, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_17, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_19, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_1, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_3, TWENTY))
    }

    if (numberToCheck.toString().endsWith("9")) {
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_9, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_11, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_13, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_17, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_19, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_1, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.TWO)
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_3, TWENTY))
        numberToCheck = numberToCheck.add(BigInteger.valueOf(4))
        threads.add(findPrimesAsync(numberToCheck, knownPrimes, foundPrimes_7, TWENTY))
    }

    threads.forEach { thread ->
        thread.join()
    }

    sw.stop()

    println()
    println(
        "Zeit für ${
            NumberFormat.getNumberInstance(Locale.GERMANY).format(NUMBERS_TO_FIND)
        } Primzahlen: ${sw.elapsedTimeMillis()} ms"
    )

    //schreibe alle gefundenen Primzahlen in die "Output"-Datei
    writeToOutputFile(outputFile_1, foundPrimes_1)
    writeToOutputFile(outputFile_3, foundPrimes_3)
    writeToOutputFile(outputFile_7, foundPrimes_7)
    writeToOutputFile(outputFile_9, foundPrimes_9)
    writeToOutputFile(outputFile_11, foundPrimes_11)
    writeToOutputFile(outputFile_13, foundPrimes_13)
    writeToOutputFile(outputFile_17, foundPrimes_17)
    writeToOutputFile(outputFile_19, foundPrimes_19)
}

private fun writeToOutputFile(outputFile_1: File, foundPrimes_1: ArrayList<String>) {
    outputFile_1.bufferedWriter().use { writer ->
        for ((index, line) in foundPrimes_1.withIndex()) {
            writer.write(line)
            // nur Zeilenumbruch schreiben, wenn es nicht die letzte Zeile ist
            if (index < foundPrimes_1.size - 1) {
                writer.write("\n")
            }
        }
    }
}

private fun findPrimesAsync(
    startPrime: BigInteger,
    knownPrimes: List<BigInteger>,
    foundPrimes: ArrayList<String>,
    increment: BigInteger
): Thread {
    val threadName = "th_$startPrime"

    val thread = Thread({
        findPrimes(
            startPrime,
            knownPrimes,
            foundPrimes,
            increment
        )
    }, threadName)

    thread.start()

    return thread
}

private fun findPrimes(
    startPrime: BigInteger,
    knownPrimes: List<BigInteger>,
    foundPrimes: ArrayList<String>,
    increment: BigInteger
) {
    var numberToCheck1 = startPrime
    var countOfFoundPrimes = 0

    do {
        val numberToCheckIsPrime = isPrime(numberToCheck1, knownPrimes)

        if (numberToCheckIsPrime) {
            countOfFoundPrimes++
            foundPrimes.add(numberToCheck1.toString())

//            if (foundPrimes.size % 10 == 0) {
//                // alle 10.000 gefundenen Primzahlen einen Punkt schreiben
//                //print(".")
//                println(Thread.currentThread().name)
//            }
        }

        numberToCheck1 = numberToCheck1.add(increment)
    } while (countOfFoundPrimes < NUMBERS_TO_FIND)
}

fun getLastPrimFile(path: String): String {

    val directory = File(path)

    if (!directory.isDirectory) {
        throw FileNotFoundException(path)
    }

    val lastPrimeFile = directory
        .listFiles { file -> file.isFile && file.extension == "prim" }
        ?.sortedBy { it.name }
        ?.lastOrNull()

    if (lastPrimeFile != null) {
        return lastPrimeFile.absolutePath
    } else {
        throw FileNotFoundException("no *.prime file found in directory '$path'")
    }
}

fun readLastLine(file: File): String? {
    RandomAccessFile(file, "r").use { raf ->
        val length = raf.length()
        if (length == 0L) return null

        var pos = length - 1
        raf.seek(pos)

        // nach Zeilenende suchen (von hinten)
        while (pos > 0) {
            val c = raf.readByte().toInt().toChar()
            if (c == '\n' || c == '\r') break
            pos--
            raf.seek(pos)
        }

        return raf.readLine()
    }
}

fun readKnownPrimes(primesPath: File): List<BigInteger> {

    val primes = ArrayList<BigInteger>(10_000_000)
    val knownPrimeFiles = listOf(
        "primes_000000001.prim",
        "primes_000000002.prim"
    )

    for (knownPrimeFileName in knownPrimeFiles) {

        val primeFile = primesPath.resolve(knownPrimeFileName)

        primeFile.useLines { lines ->
            lines.mapTo(primes) { BigInteger(it) }
        }
    }

    return primes
}

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
