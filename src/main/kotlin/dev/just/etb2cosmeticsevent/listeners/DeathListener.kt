package dev.just.etb2cosmeticsevent.listeners

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.removeCoins
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class DeathListener : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        removeCoins(event.entity, 30)
    }
}