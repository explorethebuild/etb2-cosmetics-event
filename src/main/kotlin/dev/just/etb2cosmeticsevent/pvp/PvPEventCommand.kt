package dev.just.etb2cosmeticsevent.pvp

import dev.just.etb2cosmeticsevent.Main
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class PvPEventCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            sender.sendMessage(Main.noPermission)
            return false
        }
        if (args.size != 1) {
            sender.sendMessage(Main.prefix + "${ChatColor.RED}Usage: /startpvp <Time in seconds to end>")
            return false
        }
        val timeToStop: Int? = args[0].toIntOrNull()
        if (timeToStop == null) {
            sender.sendMessage(Main.prefix + "${ChatColor.RED}Usage: /startpvp <Time in seconds to end>")
            return false
        }
        startPvPAndDisableIt(timeToStop)
        sender.sendMessage("${Main.prefix}Du hast das Event erfolgreich gestartet! ")
        return true
    }
}