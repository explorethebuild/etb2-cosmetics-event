package dev.just.etb2cosmeticsevent.shop.buyableitems

import com.github.johnnyjayjay.spiglin.item.item
import com.github.johnnyjayjay.spiglin.item.meta
import com.github.johnnyjayjay.spiglin.item.name
import dev.just.etb2cosmeticsevent.Main
import net.wesjd.anvilgui.AnvilGUI
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

val adItem: ItemStack = item(Material.PAPER) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Sende ${ChatColor.BLUE}Werbung ${ChatColor.GRAY}im Chat"
    }
}
fun removeOneAdItem(player: Player): Unit {
    for (i: Int in 0..35) {
        if (player.inventory.getItem(i)?.itemMeta?.displayName == "${ChatColor.GRAY}Sende ${ChatColor.BLUE}Werbung ${ChatColor.GRAY}im Chat"){
            player.inventory.getItem(i)!!.amount = player.inventory.getItem(i)!!.amount -1
            return
        }
    }
}

fun onClickAd(player: Player): Unit {
    removeOneAdItem(player)
    setTextItem.open(player)
}

val setTextItem: AnvilGUI.Builder = AnvilGUI.Builder()
    .onComplete { player: Player, text: String ->
        Bukkit.broadcastMessage("${ChatColor.GRAY}[${ChatColor.BLUE}Werbung${ChatColor.GRAY}] ${ChatColor.DARK_GRAY}" +
                text
        )
        player.closeInventory()
        return@onComplete AnvilGUI.Response.text("Bitte warte kurz...")
    }
    .preventClose()
    .title("Welche Nachricht soll gesendet werden?")
    .text("<Deine Werbung>")
    .plugin(Main.instance)