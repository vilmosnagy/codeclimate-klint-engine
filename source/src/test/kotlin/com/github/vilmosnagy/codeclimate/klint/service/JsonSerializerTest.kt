package com.github.vilmosnagy.codeclimate.klint.service

import com.github.vilmosnagy.codeclimate.klint.BaseTest
import com.github.vilmosnagy.codeclimate.klint.KotlinTestRunner
import com.github.vilmosnagy.codeclimate.klint.dagger.AppCtx
import com.github.vilmosnagy.codeclimate.klint.model.Config
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock

/**
 * @author Vilmos Nagy <vilmos.nagy@outlook.com>
 */
internal class JsonSerializerTest : BaseTest() {

    lateinit var testObj: JsonSerializer
        get
        private set

    override fun before() {
        super.before()
        testObj = JsonSerializer(fs, AppCtx.get.gson)
    }

    init {
        feature("Should de-serialize JSON files into data classes") {
            scenario("Should deserialize JSON file if exists") {
                val confilgFileContent = """
                    |{
                    |  "include_paths":[
                    |    ".gitignore",
                    |    "app/",
                    |    "test/"
                    |  ]
                    |}
                    """.trimMargin("|")
                createFile("/code/config.json", confilgFileContent)
                val expectedConfigFile = Config(listOf(".gitignore", "app/", "test/"))
                val actualConfigFile: Config? = testObj.parseJsonFileIfExists("/code/config.json")
                actualConfigFile shouldBe expectedConfigFile
            }

            scenario("Should return null when trying to deserialize non-existing file") {
                val actualConfigFile: Config? = testObj.parseJsonFileIfExists("/code/config.json")
                actualConfigFile shouldBe null
            }
        }
    }

}

