package dev.just.etb2cosmeticsevent.pvp

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.createTimeFromInt
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

/**
 * Shows if the PvP event is active. Controls all listeners
 */
var isPvPEventActive: Boolean = false

/**
 * The remaining time of the PvP event
 * Default / no event is 0
 */
var pvpTimer: Int = 0
/**
 * The remaining time when the event was started.
 *
 * Used for BossBar
 */
var startTime: Int = 0

/**
 * Event-only stats for kills.
 * Will be resetted when the event is over
 */
var playerKillsTroughPvP: HashMap<Player, Int> = HashMap()
/**
 * Event-only stats for deaths.
 * Will be resetted when the event is over
 */
var playerDeathsTroughPvP: HashMap<Player, Int> = HashMap()

/**
 * Bossbar that shows remaining time
 *
 * Will only be visible during the event
 */
val bossBar: BossBar = Bukkit.createBossBar("${ChatColor.GOLD}Lade...", BarColor.YELLOW, BarStyle.SEGMENTED_10)

/**
 * Enables the event and disables it after the specified time
 * @param disableAfterSeconds The amount of seconds after which the event will be disabled
 */
fun startPvPAndDisableIt(disableAfterSeconds: Int) {
    pvpTimer = disableAfterSeconds
    startTime = pvpTimer
    startPvP()
    object: BukkitRunnable() {
        override fun run() {
            pvpTimer--
            if (pvpTimer <= 0) {
                endPvP()
                pvpTimer = 0
                cancel()
            }
//            for (player: Player in Bukkit.getOnlinePlayers()) {
//                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent("${ChatColor.GRAY}" +
//                        "PvP-Event: ${ChatColor.BLUE}${ChatColor.BOLD}${createTimeFromInt(pvpTimer)}"))
//            }
            updateBossBar()
        }
    }.runTaskTimerAsynchronously(Main.instance!!, 60, 20)
}

/**
 * Starts the event.
 * Enables all listeners and informs the players.
 */

fun startPvP() {
    for (player: Player in Bukkit.getOnlinePlayers()) {
        player.sendTitle("${ChatColor.GRAY}Das ${ChatColor.BLUE}PVP-Event ${ChatColor.GRAY}hat ${ChatColor.BLUE}" +
                "gestartet", "${ChatColor.BLUE}Töte ${ChatColor.GRAY}andere und ${ChatColor.BLUE}überlebe",
                20, 60, 20)
        player.sendMessage("${Main.prefix}Detaillierte Informationen im Wiki")
        player.sendMessage("${Main.prefix}Du erhältst für jeden ${ChatColor.BLUE}Kill ${ChatColor.DARK_GRAY}an einem Spieler" +
                "${ChatColor.BLUE} 30 Coins")
        player.sendMessage("${Main.prefix}Du verlierst durch jeden ${ChatColor.BLUE}Tod ${ChatColor.DARK_GRAY}durch einen" +
                "Spieler ${ChatColor.BLUE}40 Coins")
        player.sendMessage("${Main.prefix}Du verlierst durch jeden regulären ${ChatColor.BLUE}Tod ${ChatColor.DARK_GRAY}weiterhin" +
                "Spieler ${ChatColor.BLUE}30 Coins")
        player.playSound(player.location, Sound.ENTITY_PILLAGER_CELEBRATE, 1.0F, 1.0F)
        bossBar.addPlayer(player)
    }
    isPvPEventActive = true
}

/**
 * Ends the PvP event.
 * Disables all listeners and informs the players
 */
fun endPvP() {
    isPvPEventActive = false
    for (player: Player in Bukkit.getOnlinePlayers()) {
        player.sendTitle("${ChatColor.GRAY}Das ${ChatColor.GRAY}PVP-Event ${ChatColor.GRAY}ist ${ChatColor.BLUE}" +
                "vorbei", "${ChatColor.GRAY}Es ${ChatColor.BLUE}bringt nichts${ChatColor.GRAY}" +
                " mehr, andere Spieler zu töten.",
            20, 60, 20)
        player.sendMessage("${Main.prefix}Es bringt nun nichts mehr, andere Spieler zu töten. ")
        player.sendMessage("${Main.prefix}Hoffentlich konntest du durch das Event viele Coins verdienen!")
        player.playSound(player.location, Sound.ENTITY_PILLAGER_CELEBRATE, 1.0F, 1.0F)
    }
    playerDeathsTroughPvP = HashMap()
    playerKillsTroughPvP = HashMap()
    bossBar.removeAll()
    bossBar.progress = 0.0
    bossBar.setTitle("${ChatColor.GOLD}Lade...")
//  Send notification for Github issues tab for enhancements 6 seconds after event end
    object: BukkitRunnable() {
        override fun run() {
            for (player: Player in Bukkit.getOnlinePlayers()) {
                player.sendTitle("${ChatColor.GRAY}Wie hat es dir gefallen?" +
                        "vorbei", "${ChatColor.GRAY}Sende uns ${ChatColor.BLUE}Verbesserungsvorschläge " +
                        "${ChatColor.GRAY}auf ${ChatColor.BLUE}Github",
                    20, 60, 20)
            }
            val text1: BaseComponent = TextComponent("${Main.prefix}Wie hat dir das Event gefallen? Sende ${ChatColor.BLUE}Verbesserungsvorschläge ${ChatColor.DARK_GRAY}auf ")
            val url: BaseComponent = TextComponent("${ChatColor.BLUE}GitHub")
            url.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/explorethebuild/etb2-cosmetics-event/issues")
            val text2: BaseComponent = TextComponent(" ${ChatColor.GRAY}${ChatColor.ITALIC}(klick)")
            Bukkit.spigot().broadcast(text1, url, text2)
        }
    }.runTaskLater(Main.instance!!, 120)
}

/**
 * Updates the bossbar with the current remaining time
 */
fun updateBossBar() {
    bossBar.setTitle("${ChatColor.GRAY}" +
            "PvP-Event: ${ChatColor.BLUE}${ChatColor.BOLD}${createTimeFromInt(pvpTimer)}")
    bossBar.progress = (pvpTimer * 100 / startTime) / 100.0
}

/**
 * Adds a kill to the event stats
 * @param player The player who will be added a kill
 */
fun addKill(player: Player) {
    playerKillsTroughPvP[player] = playerDeathsTroughPvP.getOrDefault(player, 0) + 1
}
/**
 * Adds a death to the event stats
 * @param player The player who will be added a death
 */
fun addDeath(player: Player) {
    playerDeathsTroughPvP[player] = playerDeathsTroughPvP.getOrDefault(player, 0) + 1
}
