package dev.butter.mathbot.math

import com.google.inject.Inject
import com.google.inject.Singleton
import dev.butter.mathbot.module.Addon
import dev.butter.mathbot.parser.Parser
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.entities.Guild

@Singleton
data class MathEventHandler
@Inject
constructor(
    private var bot: JDA,
    private var parser: Parser
) : Addon {
    private val activeSessions: MutableMap<String, MathSession> = mutableMapOf()

    fun isActive(guild: Guild) =
        guild.id in activeSessions.keys

    fun getSession(guild: Guild) =
        activeSessions[guild.id]

    fun startSession(guild: Guild) {
        activeSessions[guild.id] = MathSession()
    }

    fun endSession(guild: Guild): Long {
        val session = activeSessions[guild.id] ?: return 0
        val answer = session.solve(parser)

        activeSessions.remove(guild.id)

        return answer
    }
}