package com.github.vilmosnagy.codeclimate.klint

import com.github.vilmosnagy.codeclimate.klint.dagger.AppCtx
import com.github.vilmosnagy.codeclimate.klint.dagger.DaggerAppCtx
import com.google.common.jimfs.Configuration
import io.kotlintest.specs.FeatureSpec
import com.google.common.jimfs.Jimfs
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.runner.RunWith
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


/**
 * @author Vilmos Nagy <vilmos.nagy@outlook.com>
 */
@RunWith(KotlinTestRunner::class)
internal abstract class BaseTest : FeatureSpec() {

    lateinit var fs: FileSystem
        get
        private set

    val realAppCtx = AppCtx.get
    val mockedAppCtx: AppCtx = mock()

    private var beforeEachCallChainExecuted: Boolean = false

    final override fun beforeEach() {
        beforeEachCallChainExecuted = false
        initBaseVariables()
        before()
        if (!beforeEachCallChainExecuted) {
            throw IllegalStateException("Not called `super.before()` in the overridden `before()` method.")
        }
    }

    private fun initBaseVariables() {
        fs = Jimfs.newFileSystem(Configuration.unix())
        val gson = realAppCtx.gson
        AppCtx.mockedAppCtx = mockedAppCtx
        whenever(mockedAppCtx.fileSystem).thenReturn(fs)
        whenever(mockedAppCtx.gson).thenReturn(gson)
    }

    open fun before() {
        beforeEachCallChainExecuted = true
    }

    fun createFile(path: String, content: String, rewriteIfExists: Boolean = true): Path {
        val file = fs.getPath(path)
        Files.createDirectories(file.parent)
        Files.write(file, content.toByteArray())
        return file
    }
}