package dev.butter.mathbot.commands

import com.google.inject.Inject
import dev.butter.mathbot.math.MathEventHandler
import dev.butter.mathbot.module.CommandAddon
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import java.util.concurrent.TimeUnit

class MathEndCommand
@Inject
constructor(
    private val mathEventHandler: MathEventHandler
) : CommandAddon("end", "Ends a math session!") {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val guild = event.guild ?: return
        val channel = event.channel
        val author = event.user
        val message = event.name

        when {
            author.isBot || message != "end" -> return
            channel.name != "math" ->
                event.reply("You can only use this command in the #math channel!").queue()
            !mathEventHandler.isActive(guild) ->
                event.reply("A math session has not been started!").queue()
            else -> {
                event.reply("Ending math session!").queue()
                val answer = mathEventHandler.endSession(guild)
                channel.sendMessage("The answer is: $answer").queueAfter(1, TimeUnit.SECONDS)
            }
        }
    }
}