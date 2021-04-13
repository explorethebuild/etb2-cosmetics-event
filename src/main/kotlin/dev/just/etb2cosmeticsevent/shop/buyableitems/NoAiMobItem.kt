package dev.just.etb2cosmeticsevent.shop.buyableitems

import com.github.johnnyjayjay.spiglin.inventory.inventory
import com.github.johnnyjayjay.spiglin.inventory.set
import com.github.johnnyjayjay.spiglin.inventory.slot
import com.github.johnnyjayjay.spiglin.item.*
import net.md_5.bungee.api.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

val noAiMobItem: ItemStack = item(Material.ARMOR_STAND) {
    enchant(unsafe = true) {
        with(Enchantment.ARROW_DAMAGE) level 1
    }
    meta<ItemMeta> {
        flag(ItemFlag.HIDE_ENCHANTS)
        name = "${ChatColor.GRAY}Spawne ein ${ChatColor.BLUE}Mob ohne KI"
        lore = listOf("${ChatColor.GRAY}Mache einen ${ChatColor.BLUE}Rechtsklick${ChatColor.GRAY}, um das Item zu" +
                " benutzen. ")
    }
}

fun noAiMobInventory(): Inventory {
    return inventory(2, title = "${ChatColor.GRAY}Spawne ein ${ChatColor.BLUE}Mob ohne KI") {
//      Hostile mobs
        this[slot(0, 0)] = zombie_spawn
        this[slot(0,1)] = spider_spawn
        this[slot(0,2)] = skeleton_spawn
        this[slot(0,3)] = creeper_spawn
        this[slot(0,4)] = witch_spawn
        this[slot(0,5)] = slime_spawn
        this[slot(0,6)] = blaze_spawn
        this[slot(0,7)] = mcube_spawn
        this[slot(0,8)] = enderman_spawn
//      Friendly mobs
        this[slot(1,0)] = pig_spawn
        this[slot(1,1)] = cow_spawn
        this[slot(1,2)] = sheep_spawn
        this[slot(1,3)] = chicken_spawn
        this[slot(1,4)] = bee_spawn
        this[slot(1,5)] = fox_spawn
        this[slot(1,6)] = rabbit_spawn
        this[slot(1,7)] = horse_spawn
        this[slot(1,8)] = villager_spawn
    }
}

// Hostile
val zombie_spawn: ItemStack = item(Material.ZOMBIE_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Zombie"
    }
}
val spider_spawn: ItemStack = item(Material.SPIDER_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe eine ${ChatColor.BLUE}Spinne"
    }
}
val skeleton_spawn: ItemStack = item(Material.SKELETON_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe ein ${ChatColor.BLUE}Skellet"
    }
}
val creeper_spawn: ItemStack = item(Material.CREEPER_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Creeper"
    }
}
val witch_spawn: ItemStack = item(Material.SKELETON_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe eine ${ChatColor.BLUE}Hexe"
    }
}
val slime_spawn: ItemStack = item(Material.SLIME_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Slime"
    }
}
val blaze_spawn: ItemStack = item(Material.BLAZE_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe eine ${ChatColor.BLUE}Blaze"
    }
}
val mcube_spawn: ItemStack = item(Material.MAGMA_CUBE_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Magma Cube"
    }
}
val enderman_spawn: ItemStack = item(Material.ENDERMAN_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Enderman"
    }
}

// Friendly
val pig_spawn: ItemStack = item(Material.PIG_SPAWN_EGG) {
    meta<ItemMeta> {
        name = "${ChatColor.GRAY}Erschaffe ein ${ChatColor.BLUE}Schwein"
    }
}
val cow_spawn: ItemStack = item(Material.COW_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe eine ${ChatColor.BLUE}Kuh"
    }
}
val sheep_spawn: ItemStack = item(Material.SHEEP_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe ein ${ChatColor.BLUE}Schaf"
    }
}
val chicken_spawn: ItemStack = item(Material.CHICKEN_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe ein ${ChatColor.BLUE}Huhn"
    }
}
val bee_spawn: ItemStack = item(Material.BEE_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe eine ${ChatColor.BLUE}Biene"
    }
}
val fox_spawn: ItemStack = item(Material.FOX_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Fuchs"
    }
}
val rabbit_spawn: ItemStack = item(Material.RABBIT_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Hasen"
    }
}
val horse_spawn: ItemStack = item(Material.HORSE_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe ein ${ChatColor.BLUE}Pferd"
    }
}
val villager_spawn: ItemStack = item(Material.VILLAGER_SPAWN_EGG) {
    meta<ItemMeta> {
        name="${ChatColor.GRAY}Erschaffe einen ${ChatColor.BLUE}Dorfbewohner"
    }
}


fun onClick(player: Player): Unit {
    if (!player.inventory.containsAtLeast(noAiMobItem, 1)) return
    removeOneArmorstand(player)
    player.openInventory(noAiMobInventory())
}

fun removeOneArmorstand(player: Player): Unit {
    for (i: Int in 0..35){
        if (player.inventory.getItem(i)?.isSimilar(noAiMobItem) == true){
            player.inventory.getItem(i)!!.amount = player.inventory.getItem(i)!!.amount -1
            return
        }
    }
}

fun spawnMobWithoutAI(mobType: EntityType, player: Player): Unit {
    val spawnedEntity: Entity = player.world.spawnEntity(player.location, mobType)
    (spawnedEntity as LivingEntity).setAI(false)
    player.closeInventory()
}

class NoAiMobItem: Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.view.title == "${ChatColor.GRAY}Spawne ein ${ChatColor.BLUE}Mob ohne KI") {
            val player: Player = event.whoClicked as Player
            when (event.currentItem) {
//              Hostile mobs
                zombie_spawn -> spawnMobWithoutAI(EntityType.ZOMBIE, player)
                spider_spawn -> spawnMobWithoutAI(EntityType.SPIDER, player)
                skeleton_spawn -> spawnMobWithoutAI(EntityType.SKELETON, player)
                creeper_spawn -> spawnMobWithoutAI(EntityType.CREEPER, player)
                witch_spawn -> spawnMobWithoutAI(EntityType.WITCH, player)
                slime_spawn -> spawnMobWithoutAI(EntityType.SLIME, player)
                blaze_spawn -> spawnMobWithoutAI(EntityType.BLAZE, player)
                mcube_spawn -> spawnMobWithoutAI(EntityType.MAGMA_CUBE, player)
                enderman_spawn -> spawnMobWithoutAI(EntityType.ENDERMAN, player)
//              Friendly mobs
                pig_spawn -> spawnMobWithoutAI(EntityType.PIG, player)
                cow_spawn -> spawnMobWithoutAI(EntityType.COW, player)
                sheep_spawn -> spawnMobWithoutAI(EntityType.SHEEP, player)
                chicken_spawn -> spawnMobWithoutAI(EntityType.CHICKEN, player)
                bee_spawn -> spawnMobWithoutAI(EntityType.BEE, player)
                fox_spawn -> spawnMobWithoutAI(EntityType.FOX, player)
                rabbit_spawn -> spawnMobWithoutAI(EntityType.RABBIT, player)
                horse_spawn -> spawnMobWithoutAI(EntityType.HORSE, player)
                villager_spawn -> spawnMobWithoutAI(EntityType.VILLAGER, player)
            }
        }
    }
}