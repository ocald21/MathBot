package dev.butter.mathbot.parser

import com.google.inject.Singleton
import dev.butter.mathbot.module.Addon
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.License

@Singleton
class Parser : Addon {
    init {
        License.iConfirmNonCommercialUse("MathBot")
        License.checkIfUseTypeConfirmed()
        License.getUseTypeConfirmationMessage()
    }

    fun symbolParse(input: String) = input.replace("x", "*", true)

    fun isValid(input: String) = Expression(input).checkSyntax()

    fun solve(input: String): Long {
        val expression = Expression(input)

        val answer = expression.calculate()

        return if (answer.isNaN()) 0 else answer.toLong()
    }
}