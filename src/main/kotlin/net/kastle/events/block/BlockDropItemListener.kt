package net.kastle.events.block

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockDropItemEvent

class BlockDropItemListener : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockDropItemEvent) {
        event.isCancelled = true

        // Give the items directly to the player instead of dropping them.
        event.items.forEach { item ->
            event.player.inventory.addItem(item.itemStack)
        }
    }
}