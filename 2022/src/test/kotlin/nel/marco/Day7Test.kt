package nel.marco

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


class Day7Test {
    val dayNumber = 7

    private val example = ReadUtil.readInputAsList(dayNumber, exampleInput = true)
    private val actual = ReadUtil.readInputAsList(dayNumber, exampleInput = false)

    val dayExample = Day7(example)
    val day = Day7(actual)

    @Test
    fun processCommand() {
        val commands = """
            $ cd /
            $ ls
            165965 cmwllbzl.jlm
            68612 ggb.qgd
            dir gwnwqcgq
            dir pdlpwdp
            211084 qgcn.rbj
            dir sbps
            179881 sdpjprfb.lsh
            318082 tdhgd.lwf
            dir wvdlv
            $ cd gwnwqcgq
            $ ls
            dir btddw
            310195 cqsblt.jwb
            dir ggb
            dir hhdfbj
            dir hrj
            dir mdhln
            dir nwbndtgl
            dir pjmc
        """.trimIndent().split("\n").toList()

        val dirRegex = "(dir).+".toRegex()
        val cdBackRegex = "(# cd) (\\.\\.)".toRegex()
        val lsRegex  = "(# ls)".toRegex()
        val filesRegex = "(\\d{1,100}) (.+)".toRegex()
        val cd = "(\$ cd) (.+)".toRegex()

        println(dirRegex.find(commands[0]))
        println(cd.find(commands[0]))


    }


    @Test
    fun answerOne() {
        assertEquals(95437, dayExample.answerOne())
        assertEquals(1501149, day.answerOne())
    }

    @Test
    fun answerTwo() {

//        assertEquals(24933642, dayExample.answerTwo())
        assertEquals(10096985, day.answerTwo())
    }
}