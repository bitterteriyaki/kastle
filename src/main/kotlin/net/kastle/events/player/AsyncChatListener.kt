package net.kastle.events.player

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class AsyncChatListener : Listener {
    @EventHandler
    fun onAsyncChatEvent(event: AsyncChatEvent) {
        event.renderer { source, _, message, _ ->
            val content = PlainTextComponentSerializer
                .plainText()
                .serialize(message)
            Component.text("${source.name}: $content")
                .color(NamedTextColor.GRAY)
        }
    }
}
