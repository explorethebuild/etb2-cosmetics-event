package dev.just.etb2cosmeticsevent.utils

import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import org.bukkit.Server
import org.bukkit.block.Block
import org.bukkit.plugin.Plugin

class CoreProtectHelper(val api: CoreProtectAPI) {
    companion object {
        public fun initApi(server: Server): CoreProtectAPI? {
            val plugin: Plugin? = server.pluginManager.getPlugin("CoreProtect")

            if (plugin == null || plugin !is CoreProtect) {
                return null
            }

            val CoreProtect: CoreProtectAPI = (plugin).api
            if (!CoreProtect.isEnabled) {
                return null
            }

            if (CoreProtect.APIVersion() < 6)  {
                return null
            }

            return CoreProtect
        }
    }
    public fun testApi() {
        this.api.testAPI()
    }

    public fun isBlockPlayermade(block: Block): Boolean {
        if (lookupBlock(block).size == 0) return false
        return (parseResult(lookupBlock(block)[0]).player != null)
    }

    private fun lookupBlock(block: Block): MutableList<Array<String>> {
        return this.api.blockLookup(block, Int.MAX_VALUE)
    }

    private fun parseResult(result: Array<String>): CoreProtectAPI.ParseResult {
        return this.api.parseResult(result)
    }
}