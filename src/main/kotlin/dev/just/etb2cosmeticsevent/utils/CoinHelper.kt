package dev.just.etb2cosmeticsevent.utils

import dev.just.etb2cosmeticsevent.Main
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

public fun giveOnlinePlayersCoins(): Unit {
    object : BukkitRunnable() {
        override fun run() {
            for (player: Player in Bukkit.getOnlinePlayers()) {
                addCoins(player, 1)
            }
        }
    }.runTaskTimer(Main.instance!!, 0, 1200)
}