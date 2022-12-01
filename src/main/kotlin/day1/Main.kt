package day1

import java.io.File

val file = File("C:\\Users\\jrichard\\OneDrive - Wilson Language Training\\Documents\\input1.txt")

fun main() {
    val totals = part1().also { println(it.max()) }
    part2(totals).also { println(it) }
}

fun part1() = file.readLines().joinToString(separator = "\n").split("\n\n").map { it.split("\n").map(String::toInt).sum() }
fun part2(list: List<Int>) = list.sortedDescending().take(3).sum()