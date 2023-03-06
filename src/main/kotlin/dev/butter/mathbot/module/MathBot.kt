package dev.butter.mathbot.module

import com.google.inject.Injector
import dev.butter.mathbot.panebuilders.ErrorPane
import dev.butter.mathbot.panebuilders.StatusPane
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.File
import kotlin.system.exitProcess

class MathBot {
    private lateinit var bot: JDA
    private lateinit var injector: Injector
    private lateinit var addonLoader: AddonLoader

    fun load() {
        val tokenSuccess = validateBotToken()

        if (tokenSuccess) {
            injector = InjectorModule(bot).createInjector()
            addonLoader = injector.getInstance(AddonLoader::class.java)

            addonLoader.load()

            StatusPane("MathBot Status", "MathBot is now online!") {
                addonLoader.unload()
                bot.shutdown()
                exitProcess(0)
            }
        } else {
            ErrorPane("Enter a valid bot token in token.txt file!")
            exitProcess(1)
        }
    }

    private fun validateBotToken(): Boolean {
        val file = File("token.yml")

        if (file.exists()) {
            val tokenText = file.readText()
            val token = tokenText.substring(tokenText.indexOf(":") + 1).trim()

            return try {
                bot = JDABuilder.createDefault(token)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .build()
                true
            } catch (event: InvalidTokenException) {
                ErrorPane("Invalid token entered in token.yml!")
                false
            } catch (event: IllegalArgumentException) {
                ErrorPane("Invalid token entered in token.yml!")
                false
            }
        } else {
            file.createNewFile()
            file.writeText("token: ")
        }

        return false
    }

    companion object {
        fun log(message: String) = println("[MathBot] $message")
    }
}