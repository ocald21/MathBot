package dev.butter.mathbot.listeners

import com.google.inject.Inject
import dev.butter.mathbot.math.MathSumEvent
import dev.butter.mathbot.module.ListenerAddon
import dev.butter.mathbot.parser.Parser
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class MessageListener : ListenerAddon {
    @Inject private lateinit var mathSumEvent: MathSumEvent
    @Inject private lateinit var parser: Parser

    override fun onEvent(event: GenericEvent) {
        if (event !is MessageReceivedEvent) {
            return
        }

        val channel = event.channel
        val message = event.message
        val author = message.author

        if (channel.name != "math" ||
            mathSumEvent.messageChannel == null ||
            channel.name != mathSumEvent.messageChannel?.name ||
            author.isBot ||
            !mathSumEvent.active) {
            return
        }

        val parsedMessage = parser.symbolParse(message.contentRaw)

        if (parser.isValid(parsedMessage)) {
            message.addReaction(Emoji.fromUnicode("U+1F44D")).queue()
            mathSumEvent.equations += parsedMessage
        } else {
            message.addReaction(Emoji.fromUnicode("U+1F44E")).queue()
        }
    }
}