package dev.just.etb2cosmeticsevent.listeners

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.getCoins
import dev.just.etb2cosmeticsevent.utils.setTab
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener : Listener {
    @EventHandler
    public fun onJoin(event: PlayerJoinEvent) {
        event.player.sendMessage(Main.prefix + "Es findet aktuell das Cosmetics-Event statt!")
        val comp1: BaseComponent = TextComponent("${ChatColor.DARK_GRAY}Nutze ")
        val compc: BaseComponent = TextComponent("${ChatColor.BLUE}/eventinfo")
        val comp2: BaseComponent = TextComponent("${ChatColor.DARK_GRAY} für weitere Informationen!")
        compc.clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/eventinfo")
        event.player.spigot().sendMessage(ChatMessageType.CHAT, comp1, compc, comp2)
        setTab(event.player)
        if (!Main.canPlayerCollectPoints) {
            event.player.sendMessage("${Main.prefix}Dein finaler Punktestand lautet: ${ChatColor.BLUE}${getCoins(event.player)}")
        }
        if (Main.canPlayerLosePoints) {
            event.player.sendMessage("${Main.prefix}${ChatColor.RED}Um das Einkaufen weiter zu ermöglichen, kannst du" +
                    " jedoch weiterhin Coins verlieren! ")
        }
    }
}