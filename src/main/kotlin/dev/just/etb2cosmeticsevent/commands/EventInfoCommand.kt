package dev.just.etb2cosmeticsevent.commands

import dev.just.etb2cosmeticsevent.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class EventInfoCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        sender.sendMessage(Main.prefix + "Erhalte Punkte durch aktives Spielen und kaufe dir Cosmetics! ")
        return false
    }

}