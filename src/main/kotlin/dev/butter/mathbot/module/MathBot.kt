package dev.butter.mathbot.module

import com.authzee.kotlinguice4.getInstance
import com.google.inject.Injector
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.Bukkit.getConsoleSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class MathBot : JavaPlugin() {
    private lateinit var bot: JDA
    private lateinit var injector: Injector
    private lateinit var addonLoader: AddonLoader

    override fun onEnable() {
        val tokenSuccess = validateBotToken()

        if (!tokenSuccess) {
            return
        }

        injector = InjectorModule(bot).createInjector()
        addonLoader = injector.getInstance()
        addonLoader.load()
    }

    override fun onDisable() {
        if (!::addonLoader.isInitialized) {
            return
        }

        addonLoader.unload()
        bot.shutdown()
    }

    private fun validateBotToken(): Boolean {
        val file = File(dataFolder,"token.yml")

        if (!file.exists()) {
            File(file.parent).mkdirs()
            file.createNewFile()
            file.writeText("token: ")
            log("Token file created!")
            log("Enter a valid bot token in token.yml file!")
            server.pluginManager.disablePlugin(this)
            return false
        }

        val tokenText = file.readText()
        val token = tokenText.substring(tokenText.indexOf(":") + 1).trim()

        return try {
            bot = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build()
            true
        } catch (event: InvalidTokenException) {
            log("Invalid token entered in token.yml!")
            false
        } catch (event: IllegalArgumentException) {
            log("Invalid token entered in token.yml!")
            false
        }
    }

    companion object {
        fun log(message: String) = getConsoleSender().sendMessage("[MathBot] $message")
    }
}