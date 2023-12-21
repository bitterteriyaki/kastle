package net.kastle

import net.kastle.commands.PartyCommand
import net.kastle.events.block.BlockDropItemListener
import net.kastle.events.player.AsyncChatListener
import net.kastle.events.player.PlayerJoinListener
import net.kastle.managers.PartyManager
import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        val partyManager = PartyManager()
    }

    override fun onEnable() {
        logger.info("Kastle has been enabled.")

        /*
         * Register listeners.
         */
        val listeners = listOf(
            PlayerJoinListener(),
            AsyncChatListener(),
            BlockDropItemListener()
        )

        listeners.forEach { listener ->
            server.pluginManager.registerEvents(listener, this)
        }

        /*
         * Register commands.
         */
        val commands = hashMapOf<String, CommandExecutor>(
            "party" to PartyCommand()
        )

        commands.forEach { (name, executor) ->
            getCommand(name)?.setExecutor(executor)
        }
    }

    override fun onDisable() {
        logger.info("Kastle has been disabled.")
    }
}