package dev.just.etb2cosmeticsevent.utils

import dev.just.etb2cosmeticsevent.models.PlayerModel
import io.jsondb.InvalidJsonDbApiUsageException
import io.jsondb.JsonDBTemplate
import java.io.File

private val dbFilesLocation: String = "./plugins/cosmetics-event/"
private val baseScanPackage: String = "dev.just.etb2cosmeticsevent.models"
public val jsonDBTemplate: JsonDBTemplate = JsonDBTemplate(dbFilesLocation, baseScanPackage)
public fun init(): Unit {
    val dir: File = File("./plugins/cosmetics-event")
    if (!dir.exists()) dir.mkdirs()
//   Ignore error. Works fine in compiler
    try {
        jsonDBTemplate.createCollection(PlayerModel::class.java)
    } catch (e: InvalidJsonDbApiUsageException) {}
}