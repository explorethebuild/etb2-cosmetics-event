package dev.just.etb2cosmeticsevent.utils

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.models.PlayerModel
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

fun addCoins(player: Player, coins: Int): Unit {
    if (!Main.canPlayerCoinsChange) return
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
    sendActionbar(player, TextComponent("${ChatColor.GOLD}+${coins.toString()} ${if (coins == 1) "Coin" else "Coins"}"))
    setTab(player)
}

fun removeCoins(player: Player, coins: Int): Unit {
    if (!Main.canPlayerCoinsChange) return
    val playerModel: PlayerModel? = jsonDBTemplate.findById(player.uniqueId.toString(), PlayerModel::class.java)
    if (playerModel != null) {
        playerModel.setCoins((playerModel.getCoins()!!.toInt() - coins).toString())
        jsonDBTemplate.save<PlayerModel>(playerModel, PlayerModel::class.java)
    } else {
        val newPlayerModel: PlayerModel = PlayerModel()
        newPlayerModel.setUuid(player.uniqueId.toString())
        newPlayerModel.setCoins(coins = (coins * -1).toString())
        jsonDBTemplate.insert<PlayerModel>(newPlayerModel)
    }
    sendActionbar(player, TextComponent("${ChatColor.RED}-${coins.toString()} ${if (coins == 1) "Coin" else "Coins"} "))
    setTab(player)
}

fun getCoins(player: Player): Int {
    val playerModel: PlayerModel = jsonDBTemplate.findById(player.uniqueId.toString(), PlayerModel::class.java)
        ?: return 0
    return if (playerModel.getCoins() == null) {
        0
    } else {
        playerModel.getCoins()!!.toInt()
    }
}

fun setCoins(player: Player, coins: Int): Unit {
    if (!Main.canPlayerCoinsChange) return
    val playerModel: PlayerModel? = jsonDBTemplate.findById(player.uniqueId.toString(), PlayerModel::class.java)
    if (playerModel != null) {
        val old: Int? = playerModel.getCoins()?.toIntOrNull()
        playerModel.setCoins((coins).toString())
        jsonDBTemplate.save<PlayerModel>(playerModel, PlayerModel::class.java)
        if (coins > old!!) {
            val changedValue: Int = coins - old
            sendActionbar(player, TextComponent("${ChatColor.GOLD}+${changedValue.toString()} ${if (changedValue == 1) "Coin" else "Coins"}"))
        } else {
            val changedValue: Int = old - coins
            sendActionbar(player, TextComponent("${ChatColor.RED}-${changedValue.toString()} ${if (changedValue == 1) "Coin" else "Coins"} "))
        }
    } else {
        val newPlayerModel: PlayerModel = PlayerModel()
        newPlayerModel.setUuid(player.uniqueId.toString())
        newPlayerModel.setCoins((coins).toString())
        jsonDBTemplate.insert<PlayerModel>(newPlayerModel)
    }
    setTab(player)
}

private fun sendActionbar(player: Player, message: BaseComponent): Unit {
    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message)
}

fun setTab(player: Player) {
    val header: String = "${ChatColor.GRAY}Currently, a ${ChatColor.BLUE}COSMETICS EVENT ${ChatColor.GRAY}is running!" +
            "\nCollect ${ChatColor.BLUE}items ${ChatColor.GRAY}and kill ${ChatColor.BLUE}mobs ${ChatColor.GRAY}to " +
            "collect points!"
    val footer: String = "${ChatColor.GRAY}You currently have ${ChatColor.BLUE}${getCoins(player)} " +
            "coins${ChatColor.GRAY}!"
    player.setPlayerListHeaderFooter(header, footer)
}