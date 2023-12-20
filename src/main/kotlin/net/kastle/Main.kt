package net.kastle

import net.kastle.events.player.AsyncChatListener
import net.kastle.events.player.PlayerJoinListener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        logger.info("Kastle has been enabled.")

        server.pluginManager.apply {
            registerEvents(PlayerJoinListener(), this@Main)
            registerEvents(AsyncChatListener(), this@Main)
        }
    }

    override fun onDisable() {
        logger.info("Kastle has been disabled.")
    }
}