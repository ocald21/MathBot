package dev.butter.mathbot.math

import com.google.inject.Inject
import com.google.inject.Singleton
import dev.butter.mathbot.module.Addon
import dev.butter.mathbot.parser.Parser
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Message
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
    val messageList: MutableList<Message> = mutableListOf()
    var answer: Int = 0

    fun start(channel: MessageChannel) {
        answer = 0
        messageChannel = channel
        active = true
        messageChannel?.sendMessage("Enter your equations!")?.queue()
    }

    fun end() {
        active = false

        messageList.forEach { message ->
            val equation = message.contentRaw

            if (equation.first() != '(') {
                return@forEach
            }

            val parsedEquation = equation.replace('x', '*', ignoreCase = true)

            val answer = parser.solve(parsedEquation)

            this.answer = this.answer + answer
        }

        messageList.clear()
    }
}