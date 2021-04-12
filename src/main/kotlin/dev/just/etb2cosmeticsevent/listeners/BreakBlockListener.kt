package dev.just.etb2cosmeticsevent.listeners

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.addCoins
import dev.just.etb2cosmeticsevent.utils.removeCoins
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BreakBlockListener : Listener {
    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        if (Main.coreProtectHelper()!!.isBlockPlayermade(event.block)) return
        when (event.block.type) {
            Material.EMERALD_ORE -> addCoins(event.player, 65)
            Material.ANCIENT_DEBRIS -> addCoins(event.player, 60)
            Material.DIAMOND_ORE -> addCoins(event.player, 40)
            Material.LAPIS_ORE -> addCoins(event.player, 30)
            Material.GOLD_ORE -> addCoins(event.player, 20)
            Material.REDSTONE_ORE -> addCoins(event.player, 15)
            Material.IRON_ORE -> addCoins(event.player, 6)
            Material.NETHER_GOLD_ORE -> addCoins(event.player, 5)
            Material.OBSIDIAN -> addCoins(event.player, 5)
            Material.COAL_ORE -> addCoins(event.player, 1)
            Material.NETHER_QUARTZ_ORE -> addCoins(event.player, 1)
            Material.PRISMARINE -> removeCoins(event.player, 2)
            Material.CRYING_OBSIDIAN -> removeCoins(event.player, 3)
            else -> return
        }
    }
}