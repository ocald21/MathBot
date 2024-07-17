package dev.butter.mathbot.listeners

import com.google.inject.Inject
import dev.butter.mathbot.math.MathEventHandler
import dev.butter.mathbot.module.ListenerAddon
import dev.butter.mathbot.parser.Parser
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class MessageListener
@Inject
constructor(
    private val mathEventHandler: MathEventHandler,
    private val parser: Parser,
) : ListenerAddon {
    override fun onEvent(event: GenericEvent) {
        if (event !is MessageReceivedEvent) {
            return
        }

        val guild = event.guild
        val channel = event.channel
        val message = event.message
        val author = message.author

        if (channel.name != "math" ||
            !mathEventHandler.isActive(guild) ||
            author.isBot
        ) {
            return
        }

        val mathSession = mathEventHandler.getSession(guild) ?: return
        val parsedMessage = parser.symbolParse(message.contentRaw)

        if (parser.isValid(parsedMessage)) {
            message.addReaction(Emoji.fromUnicode("U+1F44D")).queue()
            mathSession.addEquation(parsedMessage)
        } else {
            message.addReaction(Emoji.fromUnicode("U+1F44E")).queue()
        }
    }
}