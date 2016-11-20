package com.github.vilmosnagy.codeclimate.klint.dagger

import com.github.vilmosnagy.codeclimate.klint.Main
import com.google.gson.Gson
import dagger.Component
import java.nio.file.FileSystem
import javax.inject.Singleton


/**
 * @author Vilmos Nagy {@literal <vilmos.nagy@outlook.com>}
 */
@Singleton
@Component(modules = arrayOf(DefaultDaggerModule::class))
interface AppCtx {

    val main: Main
    val fileSystem: FileSystem
    val gson: Gson

    companion object {
        val get: AppCtx
            get() = getAppCtx()

        private fun getAppCtx(): AppCtx {
            val mockedAppCtx = this.mockedAppCtx
            if (mockedAppCtx != null) {
                return mockedAppCtx
            } else {
                return DaggerAppCtx.builder().build()
            }
        }

        internal var mockedAppCtx: AppCtx? = null
    }

}