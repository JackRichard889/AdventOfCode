package day2

import java.io.File

val file = File("C:\\Users\\jrichard\\OneDrive - Wilson Language Training\\Documents\\input2.txt")

enum class Option(val value: Int) { ROCK(1), PAPER(2), SCISSORS(3), X(0), Y(0), Z(0) }
fun main() {
    println(part1())
    println(part2())
}

fun part1() : Int {
    fun score(a: Option, b: Option) : Int {
        return when {
            a == Option.ROCK && b == Option.SCISSORS -> 6
            a == Option.SCISSORS && b == Option.PAPER -> 6
            a == Option.PAPER && b == Option.ROCK -> 6
            a == b -> 3
            else -> 0
        } + a.value
    }

    return file.readLines().map { s ->
        s.split(" ").map {
            when (it) {
                "A", "X" -> Option.ROCK
                "B", "Y" -> Option.PAPER
                "C", "Z" -> Option.SCISSORS
                else -> throw Exception("What the heck happened? Got $it")
            }
        }
    }.sumOf { score(it[1], it[0]) }
}

fun part2() : Int {
    fun score(a: Option, b: Option) : Int {
        return when(b) {
            Option.X -> { 0 + when(a) {
                Option.SCISSORS -> Option.PAPER.value
                Option.ROCK -> Option.SCISSORS.value
                Option.PAPER -> Option.ROCK.value
                else -> 0
            } }
            Option.Y -> { 3 + a.value }
            Option.Z -> { 6 + when(a) {
                Option.SCISSORS -> Option.ROCK.value
                Option.ROCK -> Option.PAPER.value
                Option.PAPER -> Option.SCISSORS.value
                else -> 0
            } }
            else -> 0
        }
    }

    return file.readLines().map { s ->
        s.split(" ").map {
            when (it) {
                "A" -> Option.ROCK
                "B" -> Option.PAPER
                "C" -> Option.SCISSORS
                "X" -> Option.X
                "Y" -> Option.Y
                "Z" -> Option.Z
                else -> throw Exception("What the heck happened? Got $it")
            }
        }
    }.sumOf { score(it[0], it[1]) }
}