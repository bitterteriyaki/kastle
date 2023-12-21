package net.kastle.models

import org.bukkit.entity.Player

class Party {
    private val members = mutableListOf<Player>()
    private var partyLeader: Player? = null

    fun addMember(player: Player, partyLeader: Boolean = false) {
        members.add(player)

        if (partyLeader)
            this.partyLeader = player
    }
}
