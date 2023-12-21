package net.kastle.managers

import net.kastle.models.Party
import org.bukkit.entity.Player

class PartyManager {
    private val parties = hashMapOf<Player, Party>()
    private val invites = hashMapOf<Player, Player>()

    fun createParty(player: Player) {
        val party = Party()
        party.addMember(player, true)

        parties[player] = party
    }

    fun hasParty(player: Player): Boolean {
        return parties.containsKey(player)
    }

    fun getParty(player: Player): Party {
        return parties[player]!!
    }

    fun invitePlayer(player: Player, target: Player) {
        invites[player] = target
    }

    fun hasInvite(player: Player): Boolean {
        return invites.containsKey(player)
    }

    fun acceptInvite(player: Player) {
        val target = invites[player]!!
        val party = parties[target]!!

        party.addMember(player)
        invites.remove(player)
    }
}