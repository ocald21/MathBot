package dev.butter.mathbot.commands

import com.google.inject.Inject
import dev.butter.mathbot.module.CommandAddon
import dev.butter.mathbot.math.MathSumEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class MathStartCommand : CommandAddon("start", "Starts a math session!") {
    @Inject private lateinit var mathSumEvent: MathSumEvent

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val channel = event.channel
        val message = event.name
        val author = event.user

        when {
            author.isBot || message != "start" -> return
            channel.name != "math" ->
                event.reply("You can only use this command in the #math channel!").queue()
            mathSumEvent.active ->
                event.reply("A math session is already active!").queue()
            else -> {
                event.reply("Starting math session!").queue()
                mathSumEvent.start(channel)
            }
        }
    }
}