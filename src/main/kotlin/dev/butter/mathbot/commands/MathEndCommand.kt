package dev.butter.mathbot.commands

import com.google.inject.Inject
import dev.butter.mathbot.math.MathSumEvent
import dev.butter.mathbot.module.CommandAddon
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import java.util.concurrent.TimeUnit

class MathEndCommand : CommandAddon("end", "Ends a math session!") {
    @Inject private lateinit var mathSumEvent: MathSumEvent

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val channel = event.channel
        val author = event.user
        val message = event.name

        when {
            author.isBot || message != "end" -> return
            channel.name != "math" ->
                event.reply("You can only use this command in the #math channel!").queue()
            !mathSumEvent.active ->
                event.reply("A math session has not been started!").queue()
            else -> {
                event.reply("Ending math session!").queue()
                mathSumEvent.end()
                channel.sendMessage("The answer is: ${mathSumEvent.answer}").queueAfter(1, TimeUnit.SECONDS)
            }
        }
    }
}