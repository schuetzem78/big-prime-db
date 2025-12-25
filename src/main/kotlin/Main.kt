import java.io.*
import java.math.BigInteger
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

private const val NUMBERS_TO_FIND = 1_000_000

fun main(args: Array<String>) {

    var knownPrimesPath: String
    var primesPathName: String

    if (args.size != 2) {
        println("Die Parameter <knownPrimesPath> und <foundPrimesPath> müssen angegeben werden.");
        System.exit(-1)
        return
    } else {
        knownPrimesPath = args[0]
        primesPathName = args[1]
    }

    println("primesPath: $primesPathName")
    val primesPath = File(primesPathName)
    val lastPrimeFileName = getLastPrimFile(primesPathName)
    println("lastPrimeFileName: $lastPrimeFileName")
    val lastPrimeFile = File(lastPrimeFileName)
    val lastLine = readLastLine(lastPrimeFile)
    println("lastLine: $lastLine")
    val lastPrime = BigInteger(lastLine!!)
    val primes = readKnownPrimes(File(knownPrimesPath))
    var fileNumber = getFileNumber(lastPrimeFile.name)
    var numberToCheck = lastPrime.add(BigInteger.TWO)

    do {
        var countOfFoundPrimes = 0

        fileNumber++

        val outputFileName = getFileName(fileNumber)
        val outputFile = primesPath.resolve(outputFileName)
        val foundPrimes = ArrayList<String>()

        val sw = StopWatch()
        sw.start()

        do {
            val numberToCheckIsPrime = PrimeChecker.isPrime(numberToCheck, primes)

            if (numberToCheckIsPrime) {
                countOfFoundPrimes++
                foundPrimes.add(numberToCheck.toString())

                if (foundPrimes.size % 10_000 == 0) {
                    // alle 10.000 gefundenen Primzahlen einen Punkt schreiben
                    print(".")
                }
            }

            numberToCheck = numberToCheck.add(BigInteger.TWO);
        } while (countOfFoundPrimes < NUMBERS_TO_FIND)

        sw.stop()

        println()

        val duration: Duration = sw.elapsedTimeMillis().milliseconds

        println("Zeit für ${NumberFormat.getNumberInstance(Locale.GERMANY).format(NUMBERS_TO_FIND)} Primzahlen: ${duration.inWholeMinutes} m")

        //schreibe alle gefundenen Primzahlen in die "Output"-Datei
        outputFile.bufferedWriter().use { writer ->
            for ((index, line) in foundPrimes.withIndex()) {
                writer.write(line)
                // nur Zeilenumbruch schreiben, wenn es nicht die letzte Zeile ist
                if (index < foundPrimes.size - 1) {
                    writer.write("\n")
                }
            }
        }
    } while (true)
}

fun getFileNumber(fileName: String): Int {

    return fileName
        .removePrefix("primes_")
        .removeSuffix(".prim")
        .toInt()
}

fun getFileName(fileNumber: Int): String {

    return String.format("primes_%09d.prim", fileNumber)
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
        "primes_000000002.prim",
        "primes_000000003.prim",
        "primes_000000004.prim",
        "primes_000000005.prim")

    for (knownPrimeFileName in knownPrimeFiles) {

        val primeFile = primesPath.resolve(knownPrimeFileName)

        primeFile.useLines { lines ->
            lines.mapTo(primes) { BigInteger(it) }
        }
    }

    return primes
}
