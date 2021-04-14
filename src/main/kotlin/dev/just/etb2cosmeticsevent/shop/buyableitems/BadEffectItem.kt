package dev.just.etb2cosmeticsevent.shop.buyableitems

import com.github.johnnyjayjay.spiglin.inventory.get
import com.github.johnnyjayjay.spiglin.inventory.inventory
import com.github.johnnyjayjay.spiglin.inventory.set
import com.github.johnnyjayjay.spiglin.inventory.slot
import com.github.johnnyjayjay.spiglin.item.item
import com.github.johnnyjayjay.spiglin.item.meta
import com.github.johnnyjayjay.spiglin.item.name
import dev.just.etb2cosmeticsevent.Main
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

val badEffectItem: ItemStack = item(Material.GLASS_BOTTLE) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Effekt"
    }
}

fun removeOneBottle(player: Player): Boolean {
    for (i: Int in 0..35){
        if (player.inventory.getItem(i)?.isSimilar(badEffectItem) == true){
            player.inventory.getItem(i)!!.amount = player.inventory.getItem(i)!!.amount -1
            return true
        }
    }
    return false
}

fun onClickEffect(player: Player) {
    if (removeOneBottle(player)) player.openInventory(badEffectInventory())
}

fun badEffectInventory(): Inventory {
    return inventory(rows = 1, title = "${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Effekt") {
        this[slot(0,0)] = nauseaItem
        this[slot(0,1)] = blindnessItem
        this[slot(0,2)] = glowingItem
    }
}

fun giveEffect(player: Player, effect: PotionEffectType): Unit {
    player.addPotionEffect(PotionEffect(effect, 6000, 1))
    player.sendMessage("${Main.prefix}Du hast den Effekt ${ChatColor.BLUE}${effect.name} ${ChatColor.GRAY}erhalten.")
    player.closeInventory()
}

val nauseaItem: ItemStack = item(Material.ROTTEN_FLESH) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erhalte den Effekt ${ChatColor.BLUE}Nausea"
    }
}
val blindnessItem: ItemStack = item(Material.BLACK_DYE) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erhalte den Effekt ${ChatColor.BLUE}Blindness"
    }
}
val glowingItem: ItemStack = item(Material.SEA_LANTERN) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erhalte den Effekt ${ChatColor.BLUE}Glowing"
    }
}

class BadEffectItem: Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title == "${ChatColor.GRAY}Erhalte einen ${ChatColor.BLUE}Effekt") {
            when (event.currentItem) {
                nauseaItem -> giveEffect(event.whoClicked as Player, PotionEffectType.CONFUSION)
                blindnessItem -> giveEffect(event.whoClicked as Player, PotionEffectType.BLINDNESS)
                glowingItem -> giveEffect(event.whoClicked as Player, PotionEffectType.GLOWING)
            }
        }
    }
}