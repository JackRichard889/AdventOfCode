package day4

import java.io.File

val file = File("C:\\Users\\jackr\\Documents\\input4.txt")

fun main() {
    file.readLines().map { s ->
        val (elf1, elf2) = s.split(",")
        elf1.split("-").map(String::toInt).run { IntRange(this[0], this[1]) } to elf2.split("-").map(String::toInt).run { IntRange(this[0], this[1]) }
    }.also {
        println(part1(it))
        println(part2(it))
    }
}

fun part1(ranges: List<Pair<IntRange, IntRange>>) = ranges.count { it.first.all { i -> it.second.contains(i) } || it.second.all { i -> it.first.contains(i) } }
fun part2(ranges: List<Pair<IntRange, IntRange>>) = ranges.count { it.first.any { i -> it.second.contains(i) } || it.second.any { i -> it.first.contains(i) } }