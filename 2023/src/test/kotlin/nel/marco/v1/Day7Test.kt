package nel.marco.v1

import nel.marco.MarcoUtil
import nel.marco.ReadUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day7Test {

    var dayNumber = 7

    @ParameterizedTest
    @CsvSource(
        "AAAAA,true,false,false,false",
        "AAAA1,false,true,false,false",
        "AAA21,false,false,true,false",
        "AA321,false,false,false,true",
        "A4321,false,false,false,false",
    )
    fun XofKindTest(
        cardHand: String,
        five: Boolean,
        four: Boolean,
        three: Boolean,
        two: Boolean,
    ) {
        var cards = cardHand.split("")
            .filter { it != "" }.map {
                Card(it, 1)
            }
        val rules = Rules(cards)


        assertEquals(five, rules.hasFiveKind())
        assertEquals(four, rules.hasFourKind())
        assertEquals(three, rules.hasThreeKind())
        assertEquals(two, rules.hasTwoKind())
    }

    @ParameterizedTest
    @CsvSource(
        // strongest
        "QQQJA 1,T55J5 684,true",
        "QQQJA 1,KK677 280,true",
        "QQQJA 1,KTJJT 220,true",
        "QQQJA 1,32T3K 483,true",
        //weakest
        "32T3K 1,T55J5 684,false",
        "32T3K 1,KK677 280,false",
        "32T3K 1,KTJJT 220,false",
        "32T3K 1,QQQJA 483,false",

        )
    fun handAShouldBeatHandB(
        cardHandA: String,
        cardHandB: String,
        boolean: Boolean,
    ) {
        val playerA = cardHandA.toPlayer()
        val playerB = cardHandB.toPlayer()

        assertEquals(boolean, playerA.beats(playerB))
    }

    @ParameterizedTest
    @CsvSource(
        "AAAAA 1,beats,AAAA1 1,true",
        "AAAA1 1,beats,AAAAA 1,false",
        "AAAA1 1,beats,AAAA1 1,false",
        "AAA11 1,beats,AAA21 1,true",
        "AAA22 1,beats,AAA11 1,true",
        "AAA22 1,beats,22211 1,true",
        "AAA22 1,beats,21111 1,false",
        "AAAA2 1,beats,AAAA1 1,true",
        "TTTT2 1,beats,AAAA1 1,false",
    )
    fun handAbeatsHandB_test(
        cardHandA: String,
        randomText: String,
        cardHandB: String,
        boolean: Boolean,
    ) {

        val playerA = cardHandA.toPlayer()
        val playerB = cardHandB.toPlayer()

        assertEquals(boolean, playerA.beats(playerB))
    }

    @ParameterizedTest
    @CsvSource(
        // strongest
        "KTJJT 1,T55J5 1,true",
        "KTJJT 1,KK677 1,true",
        "KTJJT 1,QQQJA 1,true",
        "KTJJT 1,32T3K 1,true",
        //weakest
        "32T3K 1,T55J5 1,false",
        "32T3K 1,KK677 1,false",
        "32T3K 1,KTJJT 1,false",
        "32T3K 1,QQQJA 1,false",
        // custom
        "QQQQ2 1,JQQQ2 1,true",
        "QQQQ2 1,JKKK2 1,true",

    )
    fun handAShouldBeatHandB_jokerRules(
        cardHandA: String,
        cardHandB: String,
        boolean: Boolean,
    ) {
        val playerA = cardHandA.toPlayer(true)
        val playerB = cardHandB.toPlayer(true)

        assertEquals(boolean, playerA.beats(playerB))
    }

    @ParameterizedTest
    @CsvSource(
        "QQQJA 1,beats,AAAA1 1,true",
        "AAAA1 1,beats,AAAAA 1,false",
        "AAAA1 1,beats,AAAA1 1,false",
        "AAA11 1,beats,AAA21 1,true",
        "AAA22 1,beats,AAA11 1,true",
        "AAA22 1,beats,22211 1,true",
        "AAA22 1,beats,21111 1,false",
        "AAAA2 1,beats,AAAA1 1,true",
        "TTTT2 1,beats,AAAA1 1,false",
    )
    fun handAbeatsHandB_withJokerRules_test(
        cardHandA: String,
        randomText: String,
        cardHandB: String,
        boolean: Boolean,
    ) {

        val playerA = cardHandA.toPlayer()
        val playerB = cardHandB.toPlayer()

        assertEquals(boolean, playerA.beats(playerB))
    }

    @Test
    fun answerOne() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("6440", Day7(readInputExample).answerOne())
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("250957639", Day7(real).answerOne())

    }

    @Test
    fun answerTwo_exampleOnly() {
        val readInputExample = ReadUtil.readInputAsList(dayNumber, true, true)
        assertEquals("5905", Day7(readInputExample).answerTwo())

    }

    @Test
    fun answerTwo() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        assertEquals("251515496", Day7(real).answerTwo())
    }


    @Test
    fun answerTwo_benchmark() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        MarcoUtil.avgTime("answer 2", 60) {
            assertEquals("251515496", Day7(real).answerTwo())
        }
    }
}