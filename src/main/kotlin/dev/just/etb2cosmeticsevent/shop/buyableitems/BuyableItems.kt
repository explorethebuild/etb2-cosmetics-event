package dev.just.etb2cosmeticsevent.shop.buyableitems

import net.md_5.bungee.api.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class BuyableItems : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        when (event.item?.itemMeta?.displayName) {
            "${ChatColor.GRAY}Spawne ein ${ChatColor.BLUE}Mob ohne KI" -> {
                event.isCancelled = true
                onClick(event.player)
            }
            "${org.bukkit.ChatColor.GRAY}Erhalte einen ${org.bukkit.ChatColor.BLUE}Spielerkopf" -> {
                event.isCancelled = true
                onClickHead(event.player)
            }
            "${org.bukkit.ChatColor.GRAY}Erhalte einen ${org.bukkit.ChatColor.BLUE}Effekt" -> {
                event.isCancelled = true
                onClickEffect(event.player)
            }
            "${org.bukkit.ChatColor.GRAY}Sende ${org.bukkit.ChatColor.BLUE}Werbung ${org.bukkit.ChatColor.GRAY}im Chat" -> {
                event.isCancelled = true
                onClickAd(event.player)
            }
            else -> return
        }
    }
}