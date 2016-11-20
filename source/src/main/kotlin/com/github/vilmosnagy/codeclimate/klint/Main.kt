package com.github.vilmosnagy.codeclimate.klint

import com.github.shyiko.ktlint.core.KtLint
import com.github.shyiko.ktlint.core.RuleSetProvider
import com.github.vilmosnagy.codeclimate.klint.dagger.AppCtx
import com.github.vilmosnagy.codeclimate.klint.model.Config
import com.github.vilmosnagy.codeclimate.klint.service.JsonSerializer
import java.util.*
import javax.inject.Inject

/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */

class Main @Inject constructor(
        private val jsonSerializer: JsonSerializer
) {

    fun runEngine() {
        val config: Config? = jsonSerializer.parseJsonFileIfExists("/code/config.json")
        val ruleSets = ServiceLoader
                .load(RuleSetProvider::class.java)
                .map { it.get().id to it }

        println("Hello, world!")
    }

}

public fun main(args: Array<String>) {
    AppCtx.get.main.runEngine()
}