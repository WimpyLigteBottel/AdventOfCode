package nel.marco


abstract class Day(
    dayNumber: Int,
    useExample: Boolean = false,
    macBook: Boolean = true,
    open var readInput: MutableList<String> = ReadUtil.readInputAsList(dayNumber, exampleInput = useExample, macBook = macBook).toMutableList(),
) {
    fun updateReadInput(list: MutableList<String>){
        readInput = list
    }
    abstract fun answerOne(): String
    abstract fun answerTwo(): String
}