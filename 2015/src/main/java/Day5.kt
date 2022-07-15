/*
--- Day 5: Doesn't He Have Intern-Elves For This? ---
Santa needs help figuring out which strings in his text file are naughty or nice.

A nice string is one with all of the following properties:

It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
For example:

ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
jchzalrnumimnmhp is naughty because it has no double letter.
haegwjzuvuyypxyu is naughty because it contains the string xy.
dvszwmarrgswjxmb is naughty because it contains only one vowel.
How many strings are nice?
 */
class Day5 {
    fun part1(text: List<String>): Int {

        fun String.containsNumberOfVowels(amount: Int): Boolean =
            this.lowercase()
                .split("")
                .filter { it.isNotBlank() }
                .filter { it.contains("a") || it.contains("e") || it.contains("i") || it.contains("o") || it.contains("u") }
                .count() >= amount

        fun String.containsLetterThatAppearsTwice(): Boolean {
            for (letter in 'a'..'z') {
                if (this.contains("$letter$letter")) {
                    return true
                }
            }

            return false
        }

        fun String.hasNoForbiddenLetters(): Boolean {
            return setOf("ab", "cd", "pq", "xy").none { this.contains(it) }
        }

        fun String.isNice(): Boolean {

            val appearsTwice = this.containsLetterThatAppearsTwice()
            val xVowels = this.containsNumberOfVowels(3)
            val noForbiddenLetters = this.hasNoForbiddenLetters()

            return appearsTwice && xVowels && noForbiddenLetters
        }

        return text.count { it.isNice() }
    }

    /**
    Now, a nice string is one with all of the following properties:

    It contains a pair of any two letters that appears at least twice in the string without overlapping, like xyxy (xy) or aabcdefgaa (aa), but not like aaa (aa, but it overlaps).
    It contains at least one letter which repeats with exactly one letter between them, like xyx, abcdefeghi (efe), or even aaa.
    For example:

    qjhvhtzxzqqjkmpb is nice because is has a pair that appears twice (qj) and a letter that repeats with exactly one letter between them (zxz).
    xxyxx is nice because it has a pair that appears twice and a letter that repeats with one between, even though the letters used by each rule overlap.
    uurcxstgmygtbstg is naughty because it has a pair (tg) but no repeat with a single letter between them.
    ieodomkazucvgmuy is naughty because it has a repeating letter with one between (odo), but no pair that appears twice.
     */


    fun part2(text: List<String>): Int {

        fun String.containsPairThatAppearsAtLeastTwice() =
            windowed(2).any { indexOf(it) < lastIndexOf(it) - 1 }

        fun String.containsLetterRepeatsWithOneLetterInBetween() =
            windowed(3).any { it.first() == it.last() }

        return text
            .filter {
                it.containsPairThatAppearsAtLeastTwice() && it.containsLetterRepeatsWithOneLetterInBetween()
            }.count()

    }

}

fun main(args: Array<String>) {


    var readAllLines = MarcoUtil.readInput(5)
//    readAllLines = MarcoUtil.readInput(5, true)
    val day = Day5()


    val part1: () -> Unit = {
        println("part1 = ${day.part1(readAllLines)}")
    }
    val part2: () -> Unit = {
        println("part2 = ${day.part2(readAllLines)}")
    }
    MarcoUtil.time("part1", part1);
    MarcoUtil.time("part2", part2);


}






