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


        assertEquals(five, rules.ofKind(5))
        assertEquals(four, rules.ofKind(4))
        assertEquals(three, rules.ofKind(3))
        assertEquals(two, rules.ofKind(2))
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
    @Disabled
    fun answerTwo_benchmark() {
        val real = ReadUtil.readInputAsList(dayNumber, false, true)
        MarcoUtil.avgTime("answer 2", 10) {
            assertEquals("251515496", Day7(real).answerTwo())
        }
    }
}