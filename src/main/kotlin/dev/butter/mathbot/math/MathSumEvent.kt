package dev.butter.mathbot.math

import com.google.inject.Inject
import com.google.inject.Singleton
import dev.butter.mathbot.module.Addon
import dev.butter.mathbot.parser.Parser
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel

@Singleton
data class MathSumEvent
@Inject
constructor(
    private var bot: JDA,
    private var parser: Parser
) : Addon {
    var active: Boolean = false
    var messageChannel: MessageChannel? = null
    val equations: MutableList<String> = mutableListOf()
    var answer: Long = 0

    fun start(channel: MessageChannel) {
        answer = 0
        messageChannel = channel
        active = true
        messageChannel?.sendMessage("Enter your equations!")?.queue()
    }

    fun end() {
        active = false

        for (equation in equations) {
            this.answer += parser.solve(equation)
        }

        equations.clear()
    }
}