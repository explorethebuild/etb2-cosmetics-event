package dev.just.etb2cosmeticsevent
import dev.just.etb2cosmeticsevent.commands.EventInfoCommand
import dev.just.etb2cosmeticsevent.listeners.BreakBlockListener
import dev.just.etb2cosmeticsevent.listeners.JoinListener
import dev.just.etb2cosmeticsevent.listeners.MobKillListener
import dev.just.etb2cosmeticsevent.shop.GiveShopItemCommand
import dev.just.etb2cosmeticsevent.utils.CoreProtectHelper
import dev.just.etb2cosmeticsevent.utils.giveOnlinePlayersCoins
import net.coreprotect.CoreProtectAPI
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

public class Main : JavaPlugin(), Listener, CommandExecutor {
    override fun onEnable() {
        instance = this
        Bukkit.getLogger().info("Plugin loaded successfully");
        registerListeners()
        registerCommands()
        giveOnlinePlayersCoins()
        coreProtectHelper()!!.testApi()
        dev.just.etb2cosmeticsevent.utils.init()
    }

    private fun registerListeners(): Unit {
        val pluginManager: PluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(JoinListener(), this)
        pluginManager.registerEvents(BreakBlockListener(), this)
        pluginManager.registerEvents(MobKillListener(), this)
    }
    private fun registerCommands(): Unit {
        getCommand("eventinfo")?.setExecutor(EventInfoCommand())
        getCommand("giveshop")?.setExecutor(GiveShopItemCommand())
    }
    companion object {
        private fun coreProtectApi(): CoreProtectAPI? {
            return CoreProtectHelper.initApi(Bukkit.getServer())
        }
        private var cPHelper: CoreProtectHelper? = null
        fun coreProtectHelper(): CoreProtectHelper? {
            if (cPHelper == null) {
                cPHelper = CoreProtectHelper(coreProtectApi()!!)
            }
            return cPHelper
        }
        val prefix: String = "${ChatColor.GRAY}[${ChatColor.BLUE}Event${ChatColor.GRAY}]${ChatColor.DARK_GRAY} "
        var instance: Plugin? = null
        val noPlayer: String = "${prefix}${ChatColor.RED}Only players can execute this command!"
    }
}