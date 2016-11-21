package com.github.vilmosnagy.codeclimate.klint

import com.github.shyiko.ktlint.core.KtLint
import com.github.shyiko.ktlint.core.RuleSetProvider
import com.github.vilmosnagy.codeclimate.klint.dagger.AppCtx
import com.github.vilmosnagy.codeclimate.klint.model.Config
import com.github.vilmosnagy.codeclimate.klint.service.JsonSerializer
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.stream.Collectors
import javax.inject.Inject

/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */

class Main @Inject constructor(
        private val jsonSerializer: JsonSerializer
) {

    fun runEngine() {
        val config: Config? = jsonSerializer.parseJsonFileIfExists("/config.json")
        val ruleSets = ServiceLoader
                .load(RuleSetProvider::class.java)
                .map { it.get().id to it }

        Thread.sleep(180000)

        Files.walk(FileSystems.getDefault().getPath("/code"))
                .filter { p -> Files.exists(p) }
                .forEach { println("File: ${it.toAbsolutePath()}") }

        println("config: $config")
        println("----------------------")
        println("ruleSets: $ruleSets")
    }

}

public fun main(args: Array<String>) {
    AppCtx.get.main.runEngine()
}