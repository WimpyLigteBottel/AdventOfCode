import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Path
import java.security.MessageDigest

/*
--- Day 4: The Ideal Stocking Stuffer ---
Santa needs help mining some AdventCoins (very similar to bitcoins) to use as gifts for all the economically forward-thinking little girls and boys.

To do this, he needs to find MD5 hashes which, in hexadecimal, start with at least five zeroes.
 The input to the MD5 hash is some secret key (your puzzle input, given below)
  followed by a number in decimal. To mine AdventCoins, you must find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.

For example:

If your secret key is abcdef, the answer is 609043, because the MD5 hash of abcdef609043 starts with five zeroes (000001dbbfa...), and it is the lowest such number to do so.
If your secret key is pqrstuv, the lowest number it combines with to make an MD5 hash starting with five zeroes is 1048970; that is, the MD5 hash of pqrstuv1048970 looks like 000006136ef....
Your puzzle input is ckczppom.
 */
class Day4 {

    val md = MessageDigest.getInstance("MD5")

    fun String.md5(): String {
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

    fun String.hasLeadingZeros(number: Int): Boolean {
        return this.startsWith("0".repeat(number))
    }

    fun part1(text: String): Int = findLeadingZeros(text, 5);

    fun part2(text: String): Int {

        return findLeadingZeros(text, 6)

    }

    private fun findLeadingZeros(text: String, i: Int): Int {
        var counter = -1;

        while (true) {
            counter++;
            val answer = "$text$counter".md5()
            if (answer.hasLeadingZeros(i)) {
                return counter
            }
        }
    }


}

fun main(args: Array<String>) {


    var readAllLines = MarcoUtil.readInput(4)
    val day = Day4()
    val input = readAllLines.get(0)


    val part1: () -> Unit = {
        println("part1 = ${day.part1(input)}")
    }
    val part2: () -> Unit = {
        println("part2 = ${day.part2(input)}")
    }
    MarcoUtil.time("part1", part1);
    MarcoUtil.time("part2", part2);


}






