package dev.just.etb2cosmeticsevent.pvp.listeners

import dev.just.etb2cosmeticsevent.pvp.addDeath
import dev.just.etb2cosmeticsevent.pvp.addKill
import dev.just.etb2cosmeticsevent.pvp.playerKillsTroughPvP
import dev.just.etb2cosmeticsevent.utils.addCoins
import dev.just.etb2cosmeticsevent.utils.removeCoins
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class DeathAndKillListener: Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        if (event.entity.killer == null) {
            removeCoins(event.entity, 30)
        } else {
            addCoins(event.entity.killer!!, 30)
            addKill(event.entity.killer!!)
            removeCoins(event.entity, 40)
            addDeath(event.entity)
        }
    }
}