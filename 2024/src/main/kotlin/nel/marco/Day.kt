package nel.marco


abstract class Day(
    dayNumber: Int,
    useExample: Boolean = false,
    macBook: Boolean = true,
    var readInput: List<String> = ReadUtil.readInputAsList(dayNumber, exampleInput = useExample, macBook = macBook),
) {
    abstract fun answerOne(): String
    abstract fun answerTwo(): String
}