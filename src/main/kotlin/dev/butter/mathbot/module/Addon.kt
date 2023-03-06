package dev.butter.mathbot.module

import net.dv8tion.jda.api.hooks.EventListener
import net.dv8tion.jda.api.hooks.ListenerAdapter

interface Addon

interface ListenerAddon : EventListener, Addon

abstract class CommandAddon(
    val name: String,
    val description: String,
) : ListenerAdapter(), Addon
