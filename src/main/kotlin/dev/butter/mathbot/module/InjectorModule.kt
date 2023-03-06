package dev.butter.mathbot.module

import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.google.inject.Injector
import dev.butter.mathbot.commands.CommandModule
import dev.butter.mathbot.listeners.ListenerModule
import dev.butter.mathbot.math.MathModule
import dev.butter.mathbot.parser.ParserModule
import net.dv8tion.jda.api.JDA

class InjectorModule (
    private val bot: JDA
) : AbstractModule() {
    override fun configure() {
        bind(JDA::class.java).toInstance(bot)

        listOf(
            MathModule(),
            ParserModule(),
            CommandModule(),
            ListenerModule(),
        ).forEach { install(it) }
    }

    fun createInjector(): Injector = Guice.createInjector(this)
}