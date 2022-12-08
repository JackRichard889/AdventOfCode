package day7

import java.io.File

val file = File("C:\\Users\\richardj\\Documents\\input7.txt")

abstract class DirectoryOrFile {
    abstract fun size(): Int
    abstract val type: DirectoryOrFileType
    abstract val parent: DirectoryOrFile
    val children: MutableList<DirectoryOrFile> = mutableListOf()

    enum class DirectoryOrFileType { FILE, FOLDER }
}

class Directory : DirectoryOrFile() {
    override fun size() = children.sumOf(DirectoryOrFile::size)

    override val type = DirectoryOrFileType.FOLDER
    override val parent: DirectoryOrFile
        get() = TODO("Not yet implemented")
}

class File(override val parent: DirectoryOrFile, private val size: Int) : DirectoryOrFile() {
    override val type = DirectoryOrFileType.FILE
    override fun size() = size
}

fun main() {
    var currentDirectory = ""
    file.forEachLine {
        if (it.startsWith("$ cd ")) {
            when (it.split("$ cd ")[1]) {
                ".." -> {

                }
                else -> currentDirectory += "/${it.split("$ cd ")[1]}"
            }
        } else {
            when {
                it.startsWith("dir ") -> {
                    it.split("dir ")[1].run { Directory() }
                }
                it.split(" ").first().toIntOrNull() != null -> {
                    it.split(" ").run { File(Directory(), this[1].toInt()) }
                }
            }
        }
    }
}