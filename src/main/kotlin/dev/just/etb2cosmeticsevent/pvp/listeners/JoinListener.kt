package dev.just.etb2cosmeticsevent.pvp.listeners

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.pvp.bossBar
import dev.just.etb2cosmeticsevent.pvp.isPvPEventActive
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener: Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (isPvPEventActive) {
            val player: Player = event.player
            bossBar.addPlayer(player)
            player.sendTitle("${ChatColor.GRAY}Das ${ChatColor.GRAY}PVP-Event ${ChatColor.GRAY}hat ${ChatColor.BLUE}" +
                    "gestartet", "${ChatColor.BLUE}Töte ${ChatColor.GRAY}andere und ${ChatColor.BLUE}überlebe",
                20, 60, 20)
            player.sendMessage("${Main.prefix}Detaillierte Informationen im Wiki")
            player.sendMessage("${Main.prefix}Du erhältst für jeden ${ChatColor.BLUE}Kill ${ChatColor.DARK_GRAY}an einem Spieler" +
                    "${ChatColor.BLUE} 30 Coins")
            player.sendMessage("${Main.prefix}Du verlierst durch jeden ${ChatColor.BLUE}Tod ${ChatColor.DARK_GRAY}durch einen" +
                    "Spieler ${ChatColor.BLUE}40 Coins")
            player.sendMessage("${Main.prefix}Du verlierst durch jeden regulären ${ChatColor.BLUE}Tod ${ChatColor.DARK_GRAY}weiterhin" +
                    "Spieler ${ChatColor.BLUE}40 Coins")
            player.playSound(player.location, Sound.ENTITY_PILLAGER_CELEBRATE, 1.0F, 1.0F)
            bossBar.addPlayer(player)
        }
    }
}