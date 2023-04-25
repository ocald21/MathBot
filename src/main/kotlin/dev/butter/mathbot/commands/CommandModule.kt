package dev.butter.mathbot.commands

import dev.butter.mathbot.module.BaseModule

class CommandModule : BaseModule() {
    override fun configure() {
        super.configure()

        addBinding(
            MathStartCommand::class,
            MathEndCommand::class,
        )
    }
}