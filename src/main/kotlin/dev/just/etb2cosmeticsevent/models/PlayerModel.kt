package dev.just.etb2cosmeticsevent.models

import io.jsondb.annotation.Document
import io.jsondb.annotation.Id

@Document(collection = "players", schemaVersion = "1.0")
class PlayerModel {
    @Id
    private var uuid: String? = null
    private var coins: String? = null

    fun getUuid(): String? {
        return uuid
    }
    fun setUuid(UUID: String): Unit {
        this.uuid = UUID
    }

    fun getCoins(): String? {
        return coins
    }
    fun setCoins(coins: String): Unit {
        this.coins = coins
    }
 }
