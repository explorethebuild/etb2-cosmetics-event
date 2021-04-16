package dev.just.etb2cosmeticsevent.commands

import dev.just.etb2cosmeticsevent.Main
import dev.just.etb2cosmeticsevent.utils.addCoins
import dev.just.etb2cosmeticsevent.utils.getCoins
import dev.just.etb2cosmeticsevent.utils.setCoins
import dev.just.etb2cosmeticsevent.utils.setTab
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CoinManagerCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage(Main.noPermission)
            return false
        }
        if (args.size < 2) {
            sendInformation(sender)
            return false
        }
        val target: Player?
        try {
            target = Bukkit.getPlayer(args[0])
        } catch (e: Exception) {
            sendInformation(sender)
            return false
        }
        if (target == null) {
            sendInformation(sender)
            return false
        }
        when (args[1]) {
            "get" -> {
                sender.sendMessage("${Main.prefix}Der Spieler ${ChatColor.BLUE}${target.displayName}${ChatColor.GRAY}" +
                        " hat aktuell ${ChatColor.BLUE}${getCoins(target)} Coins")
                return true
            }
            "set" -> {
                if (args.size != 3) {
                    sendInformation(sender)
                    return false
                }
                val oldValue: Int = getCoins(target)
                val newValue: Int? = args[2].toIntOrNull()
                if (newValue == null) {
                    sendInformation(sender)
                    return false
                }
                setCoins(target, newValue)
                sender.sendMessage("${Main.prefix}Coins von ${ChatColor.BLUE}${target.displayName}${ChatColor.GRAY}" +
                        " erfolgreich von ${ChatColor.BLUE}${oldValue} ${ChatColor.GRAY}" +
                        "auf ${ChatColor.BLUE}${newValue} ${ChatColor.GRAY}gesetzt.")
                return true
            }
            "add" -> {
                if (args.size != 3) {
                    sendInformation(sender)
                    return false
                }
                val oldValue: Int = getCoins(target)
                val additionalValue: Int? = args[2].toIntOrNull()
                if (additionalValue == null) {
                    sendInformation(sender)
                    return false
                }
                addCoins(target, additionalValue)
                val newValue: Int = oldValue + additionalValue
                sender.sendMessage("${Main.prefix}Coins von ${ChatColor.BLUE}${target.displayName}${ChatColor.GRAY}" +
                        " erfolgreich von ${ChatColor.BLUE}${oldValue} ${ChatColor.GRAY}" +
                        "auf ${ChatColor.BLUE}${newValue} ${ChatColor.GRAY}gesetzt.")
                return true
            }
            "remove" -> {
                if (args.size != 3) {
                    sendInformation(sender)
                    return false
                }
                val oldValue: Int = getCoins(target)
                val additionalValue: Int? = args[2].toIntOrNull()
                if (additionalValue == null) {
                    sendInformation(sender)
                    return false
                }
                addCoins(target, additionalValue)
                val newValue: Int = oldValue - additionalValue
                sender.sendMessage("${Main.prefix}Coins von ${ChatColor.BLUE}${target.displayName}${ChatColor.GRAY}" +
                        " erfolgreich von ${ChatColor.BLUE}${oldValue} ${ChatColor.GRAY}" +
                        " auf ${ChatColor.BLUE}${newValue} ${ChatColor.GRAY}gesetzt.")
                return true
            }
            else -> {
                sendInformation(sender)
                return false
            }
        }
    }
    
    private fun sendInformation (sender: CommandSender) {
        sender.sendMessage("${Main.prefix}Use /managecoins <target> <get/set/add/remove> [<Value>]")
    }
}