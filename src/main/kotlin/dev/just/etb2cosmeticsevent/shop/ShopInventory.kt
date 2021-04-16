package dev.just.etb2cosmeticsevent.shop

import com.github.johnnyjayjay.spiglin.inventory.get
import com.github.johnnyjayjay.spiglin.inventory.set
import com.github.johnnyjayjay.spiglin.inventory.slot
import com.github.johnnyjayjay.spiglin.item.*
import dev.just.etb2cosmeticsevent.shop.buyableitems.adItem
import dev.just.etb2cosmeticsevent.shop.buyableitems.badEffectItem
import dev.just.etb2cosmeticsevent.shop.buyableitems.createHeadItem
import dev.just.etb2cosmeticsevent.shop.buyableitems.noAiMobItem
import dev.just.etb2cosmeticsevent.utils.getCoins
import dev.just.etb2cosmeticsevent.utils.removeCoins
import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta

fun inventory(player: Player): Inventory {
    return com.github.johnnyjayjay.spiglin.inventory.inventory(2,null, "${ChatColor.BLUE}Shop") {
        this[slot(0, 4)] = item(Material.CLOCK) {
            meta<ItemMeta> {
                name = "${ChatColor.GRAY}Du hast aktuell ${ChatColor.BLUE}${getCoins(player).toString()} Coins"
            }
        }
        this[slot(1,0)] = noAiMobShop
        this[slot(1,1)] = createHeadItemShop(player)
        this[slot(1,2)] = badEffectItemShop
        this[slot(1,3)] = adItemShop
    }
}

class ShopInventory : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            if (event.isBlockInHand) {
                val item: ItemStack = event.item!!
                if (item.type == Material.BARREL && item.hasItemMeta() && item.itemMeta!!.hasDisplayName() &&
                    item.itemMeta!!.displayName == "${ChatColor.BLUE}Shop") {
                    event.isCancelled = true
                    event.player.openInventory(inventory(event.player))
                }
            }
        }
    }

     @EventHandler
     fun onInventoryClick(event: InventoryClickEvent) {
         if (event.view.title == "${ChatColor.BLUE}Shop") {
             event.isCancelled = true
             when (event.currentItem) {
                   noAiMobShop -> {
                       if (getCoins(event.whoClicked as Player) >= 200) {
                           removeCoins(event.whoClicked as Player, 200)
                           event.whoClicked.inventory.addItem(noAiMobItem)
                       }
                   }
                   createHeadItemShop(event.whoClicked as Player) -> {
                       if (getCoins(event.whoClicked as Player) >= 100) {
                           removeCoins(event.whoClicked as Player, 100)
                           event.whoClicked.inventory.addItem(createHeadItem())
                       }
                   }
                 badEffectItemShop -> {
                     if (getCoins(event.whoClicked as Player) >= 200) {
                         removeCoins(event.whoClicked as Player, 200)
                         event.whoClicked.inventory.addItem(badEffectItem)
                     }
                 }
                 adItemShop -> {
                     if (getCoins(event.whoClicked as Player) >= 250) {
                         removeCoins(event.whoClicked as Player, 250)
                         event.whoClicked.inventory.addItem(adItem)
                     }
                 }
             }
         }
     }

}
val noAiMobShop: ItemStack = item(Material.ARMOR_STAND) {
    enchant(unsafe = true) {
        with(Enchantment.ARROW_DAMAGE) level 1
    }
    meta<ItemMeta> {
        flag(ItemFlag.HIDE_ENCHANTS)
        name = "${ChatColor.GRAY}Spawne ein ${ChatColor.BLUE}Mob ohne KI"
        lore = listOf("${ChatColor.GRAY}Du musst ${ChatColor.BLUE}200 Coins${ChatColor.GRAY} besitzen" +
                ", um dir dieses Items zu kaufen")
    }
}
fun createHeadItemShop(player: Player): ItemStack {
    return item(Material.ZOMBIE_HEAD) {
//        meta<SkullMeta> {
//            owningPlayer = player
//        }
        meta<ItemMeta> {
            name = "${org.bukkit.ChatColor.GRAY}Erhalte einen ${org.bukkit.ChatColor.BLUE}Spielerkopf"
            lore = listOf("${ChatColor.GRAY}Du musst ${ChatColor.BLUE}100 Coins${ChatColor.GRAY} besitzen" +
                    ", um dir dieses Items zu kaufen")
        }
    }
}
val badEffectItemShop: ItemStack = item(Material.GLASS_BOTTLE) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erhalte einen schlechten ${ChatColor.BLUE}Effekt"
        lore = listOf("${ChatColor.GRAY}Du musst ${ChatColor.BLUE}200 Coins${ChatColor.GRAY} besitzen" +
                ", um dir dieses Items zu kaufen")
    }
}
val adItemShop: ItemStack = item(Material.MOJANG_BANNER_PATTERN) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Schalte eine ${ChatColor.BLUE}Werbung"
        lore = listOf("${ChatColor.GRAY}Du musst ${ChatColor.BLUE}250 Coins${ChatColor.GRAY} besitzen" +
                ", um dir dieses Items zu kaufen")
    }
}