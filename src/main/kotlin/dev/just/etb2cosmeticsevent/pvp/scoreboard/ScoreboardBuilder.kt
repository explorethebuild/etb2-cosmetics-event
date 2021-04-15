package dev.just.etb2cosmeticsevent.pvp.scoreboard

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

abstract class ScoreboardBuilder(player: Player, displayName: String) {
    protected val scoreboard: Scoreboard
    protected val objective: Objective
    protected val player: Player = player

    init {
        if (player.scoreboard == Bukkit.getScoreboardManager()?.mainScoreboard) {
            player.scoreboard = Bukkit.getScoreboardManager()!!.newScoreboard
        }
        this.scoreboard = player.scoreboard

        if (this.scoreboard.getObjective("display") != null) this.scoreboard.getObjective("display")!!.unregister()

        this.objective = this.scoreboard.registerNewObjective("display", "dummy", displayName)
        this.objective.displaySlot = DisplaySlot.SIDEBAR
        this.createScoreboard()
    }

    abstract fun update()

    abstract fun createScoreboard();

    fun setDisplayName(displayName: String) {
        this.objective.displayName = displayName
    }

    fun setScore(content: String, score: Int) {
        this.objective.getScore(content).score = score
    }

    fun removeScore(content: String) {
        this.scoreboard.resetScores(content)
    }

    fun createTeam(name: String): Team {
        if (this.scoreboard.teams.contains(name)) this.scoreboard.teams.remove(this.scoreboard.getTeam(name))
        return this.scoreboard.registerNewTeam(name)
    }
}