package day5

import java.io.File
import java.util.Scanner

val file = File("C:\\Users\\richardj\\Documents\\input5.txt")

fun main() {
    val stacks: MutableList<MutableList<Char>> = mutableListOf()

    file.readLines().first { it.any(Char::isDigit) }.also {
        with (Scanner(it)) {
            while (hasNextInt()) { stacks.add(nextInt(), mutableListOf()) }
        }
    }

    file.readLines().dropWhile(String::isNotEmpty).drop(1).forEach {
        var move: Pair<Int, Int> = 0 to 0
        var count: Int

        it.substringAfter("move ").also { n ->
            count = n.toInt()
        }.substringAfter(" from ").also { n ->
            move = move.copy(first = n.toInt())
        }.substringAfter(" to ").also { n ->
            move = move.copy(second = n.toInt())
        }

        stacks[move.second - 1].addAll(stacks[move.first - 1].takeLast(count))
        stacks[move.first - 1] = stacks[move.first - 1].dropLast(count).toMutableList()
    }
}