package dev.butter.mathbot.parser

import com.google.inject.Singleton
import dev.butter.mathbot.module.Addon
import dev.butter.mathbot.module.MathBot
import org.mariuszgromada.math.mxparser.Expression
import org.mariuszgromada.math.mxparser.License

@Singleton
class Parser : Addon {
    init {
        val success = License.iConfirmNonCommercialUse("Butter Waffles")
        val confirmed = License.checkIfUseTypeConfirmed()
        val message = License.getUseTypeConfirmationMessage()

        MathBot.log("success: $success, confirmed: $confirmed, message: $message")
    }

    fun symbolParse(input: String) = input.replace("×", "*", true)

    fun isValid(input: String) = Expression(input).checkSyntax()

    fun solve(input: String): Int {
        val expression = Expression(input)

        val answer = expression.calculate()

        return if (answer.isNaN()) 0 else answer.toInt()
    }
}