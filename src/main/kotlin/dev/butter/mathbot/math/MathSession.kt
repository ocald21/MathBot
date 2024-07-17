package dev.butter.mathbot.math

import dev.butter.mathbot.parser.Parser

data class MathSession(
    val equations: MutableList<String> = mutableListOf(),
    var answer: Long = 0,
) {
    fun addEquation(equation: String) =
        equations.add(equation)

    fun solve(parser: Parser): Long =
        equations.sumOf(parser::solve)
}
