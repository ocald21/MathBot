package dev.butter.mathbot.commands

import dev.butter.mathbot.module.AddonModule

class CommandModule : AddonModule(
    MathStartCommand::class,
    MathEndCommand::class,
)