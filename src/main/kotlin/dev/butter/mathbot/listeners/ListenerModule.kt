package dev.butter.mathbot.listeners

import dev.butter.mathbot.module.BaseModule

class ListenerModule : BaseModule() {
    override fun configure() {
        super.configure()

        addBinding(
            MessageListener::class,
        )
    }
}