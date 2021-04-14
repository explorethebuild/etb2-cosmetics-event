package dev.just.etb2cosmeticsevent.shop.buyableitems

import com.github.johnnyjayjay.spiglin.item.item
import com.github.johnnyjayjay.spiglin.item.meta
import com.github.johnnyjayjay.spiglin.item.name
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import dev.just.etb2cosmeticsevent.Main
import org.bukkit.Bukkit

fun createHeadItem(): ItemStack {
    val istack: ItemStack = item(Material.PLAYER_HEAD) {
        meta<ItemMeta> {
            name = "${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Spielerkopf"
            lore = listOf(
                "${net.md_5.bungee.api.ChatColor.GRAY}Mache einen ${net.md_5.bungee.api.ChatColor.BLUE}Rechtsklick${net.md_5.bungee.api.ChatColor.GRAY}, um das Item zu" +
                        " benutzen. "
            )
        }
        meta<SkullMeta> {
            owner = "MHF_Question"
        }
    }
    val imeta: ItemMeta = istack.itemMeta!!
    imeta.setDisplayName("${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Spielerkopf")
    imeta.lore = listOf(
        "${net.md_5.bungee.api.ChatColor.GRAY}Mache einen ${net.md_5.bungee.api.ChatColor.BLUE}Rechtsklick${net.md_5.bungee.api.ChatColor.GRAY}, um das Item zu" +
                " benutzen. "
    )
    istack.itemMeta = imeta
    return istack
}

fun removeOneHead(player: Player): Unit {
    for (i: Int in 0..35) {
        if (player.inventory.getItem(i)?.itemMeta?.displayName == "${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Spielerkopf"){
            player.inventory.getItem(i)!!.amount = player.inventory.getItem(i)!!.amount -1
            return
        }
    }
}

fun onClickHead(player: Player): Unit {
    removeOneHead(player)
    selectGui
        .text(player.name)
        .open(player)
}

val selectGui : AnvilGUI.Builder = AnvilGUI.Builder()
    .onComplete { player: Player, text: String ->
        player.inventory.addItem(
            item(Material.PLAYER_HEAD) {
                meta<ItemMeta> {
                    name = "${ChatColor.GRAY}Kopf von ${ChatColor.BLUE}${text}"
                }
                meta<SkullMeta> {
                    owner = text
                }
            }
        )
        return@onComplete AnvilGUI.Response.close()
    }
    .preventClose()
    .title("Welchem Spieler soll der Kopf gehören?")
    .plugin(Main.instance)