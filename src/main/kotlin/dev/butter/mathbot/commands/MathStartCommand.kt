package dev.butter.mathbot.commands

import com.google.inject.Inject
import dev.butter.mathbot.math.MathEventHandler
import dev.butter.mathbot.module.CommandAddon
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class MathStartCommand
@Inject
constructor(
    private val mathEventHandler: MathEventHandler
) : CommandAddon("start", "Starts a math session!") {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val guild = event.guild ?: return
        val channel = event.channel
        val message = event.name
        val author = event.user

        when {
            author.isBot || message != "start" -> return
            channel.name != "math" ->
                event.reply("You can only use this command in the #math channel!").queue()
            mathEventHandler.isActive(guild) ->
                event.reply("A math session is already active!").queue()
            else -> {
                event.reply("Starting math session!").queue()
                mathEventHandler.startSession(guild)
            }
        }
    }
}