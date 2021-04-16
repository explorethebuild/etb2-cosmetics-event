package dev.just.etb2cosmeticsevent.pvp.scoreboard

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

/**
 * Creates individual scoreboard for each player
 * @author justCoding
 * @author DerBanko
 * @param player The player the scoreboard will be visible for
 * @param displayName The displayname or title of the created scoreboard
 */

abstract class ScoreboardBuilder(player: Player, displayName: String) {
    /**
     * Scoreboard that will be visible to the player
     */
    protected val scoreboard: Scoreboard

    /**
     * The sidebar objective
     */
    protected val objective: Objective

    /**
     * The player that the scoreboard is presented to
     */
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

    /**
     * Called after the scoreboard and objectives were created.
     *
     * Start here to set scores
     */
    abstract fun createScoreboard();

    /**
     * Sets the displayname or title of the scoreboard
     */
    fun setDisplayName(displayName: String) {
        this.objective.displayName = displayName
    }

    /**
     * Sets a score in the scoreboard
     * @param content The actual content that will be presented to the player.
     * Equal to the player name in a normal scoreboard
     * @param score The score the content has.
     * You will set lower values for scores that will be placed lower in the scoreboard
     */

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