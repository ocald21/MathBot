package dev.butter.mathbot.module

import com.google.inject.Inject
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.interactions.commands.Command

class AddonLoader
@Inject
constructor(
    private val addons: MutableSet<Addon>,
    private val bot: JDA
) {
    fun load() {
        var botCommands: MutableList<Command?>? = null

        bot.retrieveCommands().queue {
            botCommands = it
        }

        addons.forEach { addon ->
            when (addon) {
                is CommandAddon -> {
                    bot.addEventListener(addon)

                    botCommands
                        ?.none { it?.name == addon.name }
                        ?.run { return@forEach }

                    bot.upsertCommand(addon.name, addon.description).queue()

                    bot.guilds.forEach { guild ->
                        guild.retrieveCommands().queue { commands ->
                            commands
                                .find { it.name == addon.name }
                                ?.run { return@queue }

                            guild.upsertCommand(addon.name, addon.description).queue()
                        }
                    }
                }
                is ListenerAddon -> bot.addEventListener(addon)
            }
        }
    }

    fun unload() {
        addons.forEach { addon ->
            when (addon) {
                is CommandAddon -> bot.removeEventListener(addon)
                is ListenerAddon -> bot.removeEventListener(addon)
            }
        }
    }
}