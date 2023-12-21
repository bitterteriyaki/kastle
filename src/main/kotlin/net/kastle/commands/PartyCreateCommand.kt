package net.kastle.commands

import net.kastle.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class PartyCreateCommand : TabExecutor {
    private val partyManager = Main.partyManager

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        return null
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (args != null && args.isNotEmpty())
            return false

        if (sender !is Player) {
            val component = Component
                .text("Somente jogadores podem criar parties.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        if (partyManager.hasParty(sender)) {
            val component = Component
                .text("Você já está em uma party.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        partyManager.createParty(sender)

        val component = Component
            .text("Você criou uma party com sucesso.")
            .color(NamedTextColor.YELLOW)
        sender.sendMessage(component)

        return true
    }
}