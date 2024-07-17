package dev.butter.mathbot.listeners

import dev.butter.mathbot.module.AddonModule

class ListenerModule : AddonModule(
    MessageListener::class,
)