package nel.marco


class Day7(var readInput: List<String>) {

    var listOfDirectories = mutableListOf<DirectoryNode>()

    companion object {
        val day: Int = 7
    }

    fun answerOne(): Int {
        val maxSize = 100000

        setupDirStructure()


        return listOfDirectories.filter {
            it.totalSize < maxSize
        }.sumOf { it.totalSize }

    }


    fun answerTwo(): Int {

        val maxSize = 30000000

        val parent = setupDirStructure()
        var available = 70000000 - parent.totalSize


        val largestDir = listOfDirectories.filter {
            it.totalSize + available >= maxSize
        }.sortedBy { it.totalSize }

        return largestDir[0].totalSize

    }


    private fun setupDirStructure(): DirectoryNode {
        val parent = DirectoryNode("/", null, mutableListOf(), mutableListOf())


        var current: DirectoryNode = parent
        current.previous = null

        val dirRegex = "(dir).+".toRegex()
        val cdBackRegex = "(# cd) (\\.\\.)".toRegex()
        val cdDirectoryRegex = "(# cd) (\\w+)".toRegex()
        val lsRegex = "(# ls)".toRegex()
        val filesRegex = "(\\d{1,100}) (.+)".toRegex()


        var lsUse = false
        readInput.forEach { input ->


            val dir = dirRegex.find(input)
            val file = filesRegex.find(input)

            val backOneDirCommand = cdBackRegex.find(input)
            val changeDirectoryCommand = cdDirectoryRegex.find(input)
            val lsCommand = lsRegex.find(input)
            val baseDirCommand = "# cd /".toRegex().find(input)


            if (backOneDirCommand != null || changeDirectoryCommand != null || lsCommand != null || baseDirCommand != null) {
                lsUse = false
                lsCommand?.let {
                    lsUse = true
                }
                baseDirCommand?.let {
                    lsUse = true
                }
                backOneDirCommand?.let {
                    current.setTotalSize()
                    current = current.previous!!
                }
                changeDirectoryCommand?.let { outer ->

                    current = current.node.find { it -> it.currentName == outer.destructured.component2() }!!
                }
            } else {
                if (lsUse) {
                    dir?.let {
                        val name = input.split("dir ")[1]
                        val element = DirectoryNode(name, current, mutableListOf(), mutableListOf())
                        current.node.add(element)
                        listOfDirectories.add(element)
                    }

                    file?.let {
                        val nameAndSize = input.split(" ")
                        val name = nameAndSize[1]
                        val size = nameAndSize[0].toInt()
                        current.files.add(File(name, size))
                    }
                }
            }

//            if (input.startsWith("#")) {
//                lsUse = false
//                if (input == "# ls") {
//                    lsUse = true
//                } else if (input.startsWith("# cd /")) {
//                    current = parent
//                } else if (input.startsWith("# cd ..")) {
//                    current.setTotalSize()
//                    current = current.previous!!
//                } else if (input.startsWith("# cd ")) {
//                    current = current.node.find { it.currentName == input.split("#cd ")[1] }!!
//                }
//            } else {
//                if (lsUse) {
//                    if (input.startsWith("dir")) {
//                        val name = input.split("dir ")[1]
//                        val element = DirectoryNode(name, current, mutableListOf(), mutableListOf())
//                        current.node.add(element)
//                        listOfDirectories.add(element)
//                    } else {
//                        val nameAndSize = input.split(" ")
//                        val name = nameAndSize[1]
//                        val size = nameAndSize[0].toInt()
//                        current.files.add(File(name, size))
//                    }
//                }
//            }
        }
        return parent
    }


}

class DirectoryNode(
    var currentName: String = "/",
    var previous: DirectoryNode?,
    var node: MutableList<DirectoryNode> = mutableListOf(),
    var files: MutableList<File> = mutableListOf(),
    var totalSize: Int = 0
) {

    override fun toString(): String {
        return "DirectoryNode(currentName='$currentName', previous=$previous, files=$files)"
    }

    fun calculateTotalSize(): Int {
        val sumOf = files.sumOf { it.size }

        return sumOf
    }

    fun setTotalSize() {
        totalSize += calculateTotalSize()

        previous?.totalSize = previous?.totalSize?.plus(totalSize)!!
    }
}

data class File(val name: String, val size: Int)

fun main(args: Array<String>) {
    var readAllLines = MarcoUtil.readInput(Day7.day, false) as MutableList<String>

    val part1: () -> Unit = {
        val day = Day7(readAllLines)
        println("part1 = ${day.answerOne()}")
    }
    val part2: () -> Unit = {
        val day = Day7(readAllLines)
        println("part2 = ${day.answerTwo()}")
    }

    MarcoUtil.time("part1", part1)
    MarcoUtil.time("part2", part2)

}