package dev.butter.mathbot.parser

import dev.butter.mathbot.module.BaseModule

class ParserModule : BaseModule() {
    override fun configure() {
        super.configure()

        addBinding(
            Parser::class.java,
        )
    }
}