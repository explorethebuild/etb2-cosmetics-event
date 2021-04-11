package dev.just.etb2cosmeticsevent.listeners

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.addCoins
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BreakBlockListener : Listener {
    @EventHandler
    public fun onBreak(event: BlockBreakEvent) {
        if (Main.coreProtectHelper()!!.isBlockPlayermade(event.block)) return
        if (event.block.type.equals(Material.DIAMOND_ORE)) addCoins(event.player,40)
        else if (event.block.type.equals(Material.EMERALD_ORE)) addCoins(event.player, 60)
        else if (event.block.type.equals(Material.IRON_ORE)) addCoins(event.player, 10)
        else if (event.block.type.equals(Material.COAL_ORE)) addCoins(event.player, 3)
    }
}