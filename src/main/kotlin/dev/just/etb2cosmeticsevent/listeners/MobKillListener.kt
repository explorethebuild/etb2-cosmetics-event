package dev.just.etb2cosmeticsevent.listeners

import dev.just.etb2cosmeticsevent.utils.addCoins
import dev.just.etb2cosmeticsevent.utils.removeCoins
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class MobKillListener: Listener {
    @EventHandler
    public fun onMobKill(event: EntityDamageByEntityEvent) {
        if (event !is LivingEntity)
        if (event.finalDamage < (event.entity as LivingEntity).health) return
        if (event.damager !is Player) return
        val entityType: EntityType = event.entity.type
        val player: Player = event.damager as Player
        when (entityType) {
            EntityType.ENDER_DRAGON -> addCoins(player, 200)
            EntityType.WITHER -> addCoins(player, 150)
            EntityType.ELDER_GUARDIAN -> addCoins(player, 75)
            EntityType.PIGLIN_BRUTE -> addCoins(player, 75)
            EntityType.EVOKER -> addCoins(player, 60)
            EntityType.SHULKER -> addCoins(player, 30)
            EntityType.WITCH -> addCoins(player, 10)
            EntityType.WITHER_SKELETON -> addCoins(player, 10)
            EntityType.BLAZE -> addCoins(player, 8)
            EntityType.GHAST -> addCoins(player, 8)
            EntityType.MAGMA_CUBE -> addCoins(player, 8)
            EntityType.SLIME -> addCoins(player, 6)
            EntityType.IRON_GOLEM -> addCoins(player,5)
            EntityType.CREEPER -> addCoins(player,4)
            EntityType.PHANTOM -> addCoins(player, 3)
            EntityType.SKELETON -> addCoins(player ,2)
            EntityType.BOAT -> removeCoins(player, 2)
            EntityType.VILLAGER -> removeCoins(player, 100)
            else -> return
        }
    }
}