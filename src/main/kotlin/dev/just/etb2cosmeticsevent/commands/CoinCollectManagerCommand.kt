package dev.just.etb2cosmeticsevent.commands

import dev.just.etb2cosmeticsevent.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CoinCollectManagerCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) sender.sendMessage(Main.noPermission)
        else {
            if (args.size == 1) {
                if (args[0] == "collect") {
                    sender.sendMessage("${Main.prefix}${if (Main.canPlayerCollectPoints) "Spieler können Punkte sammeln"
                    else "Spieler können keine Punkte sammeln"}")
                } else if (args[0] == "remove") {
                    sender.sendMessage("${Main.prefix}${if (Main.canPlayerLosePoints) "Spieler können Punkte verlieren"
                    else "Spieler können keine Punkte verlieren. "}")
                }
            } else if (args.size == 2) {
                if (args[0] == "collect") {
                    try {
                        Main.canPlayerCollectPoints = args[1].toBoolean()
                    } catch (e: Exception) {}
                } else if (args[0] == "remove") {
                    try {
                        Main.canPlayerLosePoints = args[1].toBoolean()
                    } catch (e: Exception) {}
                }
            }
        }
        return true
    }
}