package dev.butter.mathbot.math

import dev.butter.mathbot.module.BaseModule

class MathModule : BaseModule() {
    override fun configure() {
        super.configure()

        addBinding(
            MathSumEvent::class.java
        )
    }
}