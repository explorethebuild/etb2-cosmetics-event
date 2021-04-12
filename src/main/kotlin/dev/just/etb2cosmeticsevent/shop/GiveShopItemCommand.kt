package dev.just.etb2cosmeticsevent.shop

import com.github.johnnyjayjay.spiglin.item.*
import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.getCoins
import dev.just.etb2cosmeticsevent.utils.removeCoins
import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.lang.reflect.Modifier

class GiveShopItemCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Main.noPlayer)
            return false
        }
        val player: Player = sender
        if (!isPlayerEligible(player)) {
            sender.sendMessage("${Main.prefix + ChatColor.RED}Du benötigst mindestens 100 Coins, um dir den Shop zu " +
                    "kaufen!")
            return false
        }
        val item: ItemStack = item(Material.BARREL) {
            enchant(unsafe = true) {
                with(Enchantment.ARROW_DAMAGE) level 1
            }
            meta<ItemMeta> {
                name = "${ChatColor.BLUE}Shop"
                flag(ItemFlag.HIDE_ENCHANTS)
                flag(ItemFlag.HIDE_ATTRIBUTES)
            }
        }
        removeCoins(player, 100)
        player.inventory.addItem(item)
        sender.sendMessage("${Main.prefix}Du hast einen Shop erhalten. Verliere ihn nicht! ")
        return true
    }

    private fun isPlayerEligible(player: Player): Boolean {
        return player.isOp || getCoins(player) >= 100
    }
}