package dev.just.etb2cosmeticsevent.pvp.scoreboard

import dev.just.etb2cosmeticsevent.pvp.playerDeathsTroughPvP
import dev.just.etb2cosmeticsevent.pvp.playerKillsTroughPvP
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team

class PvPScoreboard(player: Player) : ScoreboardBuilder(player, "${ChatColor.BLUE}${ChatColor.BOLD}PvP-Event"){

    override fun update() {}

    override fun createScoreboard() {
        setScore("${ChatColor.GRAY}Töte Spieler für Coins.", 5)
        setScore("${ChatColor.GRAY}Deine Kills:", 4)
        setScore("${ChatColor.BLUE}${playerKillsTroughPvP.getOrDefault(player, 0)} ${ChatColor.BLACK}", 3)
        setScore("${ChatColor.DARK_AQUA}", 2)
        setScore("${ChatColor.GRAY}Deine Deaths durch Spieler:", 1)
        setScore("${ChatColor.BLUE}${playerDeathsTroughPvP.getOrDefault(player, 0)} ${ChatColor.WHITE}", 0)

    }
}