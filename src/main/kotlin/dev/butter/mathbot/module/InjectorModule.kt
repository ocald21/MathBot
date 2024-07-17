package dev.butter.mathbot.module

import com.authzee.kotlinguice4.KotlinModule
import com.google.inject.Guice
import com.google.inject.Injector
import dev.butter.mathbot.commands.CommandModule
import dev.butter.mathbot.listeners.ListenerModule
import dev.butter.mathbot.math.MathModule
import dev.butter.mathbot.parser.ParserModule
import net.dv8tion.jda.api.JDA

class InjectorModule(private val bot: JDA) : KotlinModule() {
    override fun configure() {
        bind(JDA::class.java).toInstance(bot)

        listOf(
            MathModule(),
            ParserModule(),
            CommandModule(),
            ListenerModule(),
        ).forEach(::install)
    }

    fun createInjector(): Injector = Guice.createInjector(this)
}