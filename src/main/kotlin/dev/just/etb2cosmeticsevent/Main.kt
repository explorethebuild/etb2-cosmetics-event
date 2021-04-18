package dev.just.etb2cosmeticsevent
import dev.just.etb2cosmeticsevent.commands.CoinCollectManagerCommand
import dev.just.etb2cosmeticsevent.commands.CoinManagerCommand
import dev.just.etb2cosmeticsevent.commands.EventInfoCommand
import dev.just.etb2cosmeticsevent.listeners.BreakBlockListener
import dev.just.etb2cosmeticsevent.listeners.DeathListener
import dev.just.etb2cosmeticsevent.listeners.JoinListener
import dev.just.etb2cosmeticsevent.listeners.MobKillListener
import dev.just.etb2cosmeticsevent.pvp.PvPEventCommand
import dev.just.etb2cosmeticsevent.pvp.listeners.DeathAndKillListener
import dev.just.etb2cosmeticsevent.shop.GiveShopItemCommand
import dev.just.etb2cosmeticsevent.shop.ShopInventory
import dev.just.etb2cosmeticsevent.shop.buyableitems.BadEffectItem
import dev.just.etb2cosmeticsevent.shop.buyableitems.BuyableItems
import dev.just.etb2cosmeticsevent.shop.buyableitems.NoAiMobItem
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
        pluginManager.registerEvents(DeathListener(), this)
        pluginManager.registerEvents(ShopInventory(), this)
        pluginManager.registerEvents(NoAiMobItem(), this)
        pluginManager.registerEvents(BuyableItems(), this)
        pluginManager.registerEvents(BadEffectItem(), this)
        pluginManager.registerEvents(DeathAndKillListener(), this)
        pluginManager.registerEvents(dev.just.etb2cosmeticsevent.pvp.listeners.JoinListener(), this)
    }
    private fun registerCommands(): Unit {
        getCommand("eventinfo")?.setExecutor(EventInfoCommand())
        getCommand("giveshop")?.setExecutor(GiveShopItemCommand())
        getCommand("managecoins")?.setExecutor(CoinManagerCommand())
        getCommand("startpvp")?.setExecutor(PvPEventCommand())
        getCommand("coincollectmanager")?.setExecutor(CoinCollectManagerCommand())
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
        /**
         * Message that says only players can execute the command
         */
        val noPlayer: String = "${prefix}${ChatColor.RED}Only players can execute this command!"
        /**
         * Message that says the player hasn't the permissions for that command
         */
        val noPermission: String = "${prefix}${ChatColor.RED}You don't have the permissions to execute this command!"
        var canPlayerCollectPoints: Boolean = false
        var canPlayerLosePoints: Boolean = true
    }
}