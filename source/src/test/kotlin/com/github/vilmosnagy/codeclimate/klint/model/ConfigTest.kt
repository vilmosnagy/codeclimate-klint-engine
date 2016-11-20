package com.github.vilmosnagy.codeclimate.klint.model

import com.github.vilmosnagy.codeclimate.klint.BaseTest

import org.junit.Assert.*

/**
 * @author Vilmos Nagy  <vilmos.nagy></vilmos.nagy>@outlook.com>
 */
internal class ConfigTest : BaseTest() {

    init {
        feature("Methods in Config should work") {
            scenario("Should resolve included paths correctly") {
                val testObj = Config(listOf(".gitignore"))
                val path = fs.getPath(".gitignore")
                testObj.includedPaths shouldBe listOf(path)
            }
        }
    }
}