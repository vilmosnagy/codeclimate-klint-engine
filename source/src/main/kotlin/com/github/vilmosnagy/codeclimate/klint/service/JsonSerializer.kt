package com.github.vilmosnagy.codeclimate.klint.service

import com.google.gson.Gson
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Paths
import javax.inject.Inject

/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */
class JsonSerializer @Inject constructor(
        private val fileSystem: FileSystem,
        private val gson: Gson
) {
    inline fun <reified T: Any> parseJsonFileIfExists(filePath: String): T? {
        val clazz: Class<T> = T::class.java
        return parseJsonFileIfExists(filePath, clazz)
    }

    fun <T> parseJsonFileIfExists(filePath: String, clazz: Class<T>): T? {
        val path = fileSystem.getPath(filePath)
        return if (Files.exists(path) && Files.isRegularFile(path)) {
            val content = String(Files.readAllBytes(path))
            gson.fromJson(content, clazz)
        } else {
            null
        }
    }

}