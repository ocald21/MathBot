package dev.butter.mathbot.math

import dev.butter.mathbot.module.AddonModule

class MathModule : AddonModule(
    MathEventHandler::class
)