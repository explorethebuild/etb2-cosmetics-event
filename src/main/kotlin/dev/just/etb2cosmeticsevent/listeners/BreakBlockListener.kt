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
    public fun onBreak(event: BlockBreakEvent) {
        if (Main.coreProtectHelper()!!.isBlockPlayermade(event.block)) return
        if (event.block.type.equals(Material.DIAMOND_ORE)) addCoins(event.player,40)
        else if (event.block.type.equals(Material.EMERALD_ORE)) addCoins(event.player, 60)
        else if (event.block.type.equals(Material.IRON_ORE)) addCoins(event.player, 10)
        else if (event.block.type.equals(Material.COAL_ORE)) addCoins(event.player, 3)
        else if (event.block.type.equals(Material.OBSIDIAN)) addCoins(event.player, 5)
        else if (event.block.type.equals(Material.LAPIS_ORE)) addCoins(event.player, 6)
        else if (event.block.type.equals(Material.ANCIENT_DEBRIS)) addCoins(event.player, 70)
        else if (event.block.type.equals(Material.CRYING_OBSIDIAN)) removeCoins(event.player, 3)
        else if (event.block.type.equals(Material.PRISMARINE)) removeCoins(event.player, 2)
    }
}