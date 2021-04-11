package dev.just.etb2cosmeticsevent.utils

import dev.just.etb2cosmeticsevent.models.PlayerModel
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

public fun addCoins(player: Player, coins: Int): Unit {
    sendActionbar(player, TextComponent("${ChatColor.GOLD}+${coins.toString()} ${if (coins == 1) "Coin" else "Coins"}"))
    val playerModel: PlayerModel? = jsonDBTemplate.findById(player.uniqueId.toString(), PlayerModel::class.java)
    if (playerModel == null) {
        val newPlayerModel: PlayerModel = PlayerModel()
        newPlayerModel.setUuid(player.uniqueId.toString())
        newPlayerModel.setCoins(coins = coins.toString())
        jsonDBTemplate.insert<PlayerModel>(newPlayerModel)
    } else {
        playerModel.setCoins((playerModel.getCoins()!!.toInt() + coins).toString())
        jsonDBTemplate.save<PlayerModel>(playerModel, PlayerModel::class.java)
    }
}

public fun removeCoins(player: Player, coins: Int): Unit {
    sendActionbar(player, TextComponent("${ChatColor.RED}-${coins.toString()} Coins"))
}

private fun sendActionbar(player: Player, message: BaseComponent): Unit {
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message)
}