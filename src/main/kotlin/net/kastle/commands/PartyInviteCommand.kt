package net.kastle.commands

import net.kastle.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class PartyInviteCommand : TabExecutor {
    private val partyManager = Main.partyManager

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String> {
        return Bukkit.getServer().onlinePlayers.map { it.name }.toMutableList()
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): Boolean {
        if (args == null || args.isEmpty())
            return false

        if (sender !is Player) {
            val component = Component
                .text("Somente jogadores podem convidar para parties.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        if (!partyManager.hasParty(sender)) {
            val component = Component
                .text("Você não está em uma party.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        val target = Bukkit.getPlayer(args[0])

        if (target == null) {
            val component = Component
                .text("Jogador não encontrado.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        if (target == sender) {
            val component = Component
                .text("Você não pode convidar você mesmo.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        if (partyManager.hasParty(target)) {
            val component = Component
                .text("Jogador já está em uma party.")
                .color(NamedTextColor.RED)
            sender.sendMessage(component)
            return true
        }

        partyManager.invitePlayer(sender, target)

        sender.sendMessage(
            Component.text("Você convidou o jogador ")
                .color(NamedTextColor.YELLOW)
                .append(Component.text(target.name).color(NamedTextColor.AQUA))
                .append(Component.text(" para a sua party.").color(NamedTextColor.YELLOW))
        )

        target.sendMessage(
            Component.text("Você foi convidado para a party de ")
                .color(NamedTextColor.YELLOW)
                .append(Component.text(sender.name).color(NamedTextColor.AQUA))
                .append(Component.text(".").color(NamedTextColor.YELLOW))
        )

        return true
    }
}