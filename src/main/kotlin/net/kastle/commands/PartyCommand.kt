package net.kastle.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class PartyCommand : TabExecutor {
    private val executors = hashMapOf<String, TabExecutor>(
        "create" to PartyCreateCommand(),
        "invite" to PartyInviteCommand()
    )

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        if (args == null || args.isEmpty())
            return mutableListOf()

        if (args.size == 1)
            return executors.keys.filter { it.startsWith(args[0]) }.toMutableList()

        val subcommand = executors[args[0]] ?: return mutableListOf()
        return subcommand.onTabComplete(sender, command, label, args.sliceArray(1 until args.size))
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): Boolean {
        if (args == null || args.isEmpty())
            return false

        val subcommand = executors[args[0]] ?: return false
        return subcommand.onCommand(sender, command, label, args.sliceArray(1 until args.size))
    }
}
