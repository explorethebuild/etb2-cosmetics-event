package dev.just.etb2cosmeticsevent.listeners

import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MobKillListener: Listener {
    @EventHandler
    public fun onMobKill(event: EntityDamageByEntityEvent) {
        if (!event.entity.isDead) return
        if (event.damager !is Player) return
        val entityType: EntityType = event.entity.type
    }
}