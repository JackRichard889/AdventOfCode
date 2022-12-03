package day3

import java.io.File

val file = File("C:\\Users\\jackr\\Documents\\input3.txt")

fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    var runningTotal = 0
    file.forEachLine {
        val (half1, half2) = it.chunked(it.length / 2)
        runningTotal += half1.filter { c -> half2.contains(c) }.toCharArray().distinct().sumOf { c -> c.code - if(c.isUpperCase()) 38 else 96 }
    }
    return runningTotal
}

fun part2() = file.readLines().chunked(3).sumOf {
    it[0].filter { c -> it[1].contains(c) && it[2].contains(c) }.toCharArray().distinct().sumOf { c -> c.code - if(c.isUpperCase()) 38 else 96 }
}