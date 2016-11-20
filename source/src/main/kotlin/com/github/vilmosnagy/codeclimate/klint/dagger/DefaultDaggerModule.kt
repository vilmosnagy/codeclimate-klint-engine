package com.github.vilmosnagy.codeclimate.klint.dagger

import com.github.vilmosnagy.codeclimate.klint.gson.SerializedNameOnlyStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import javax.inject.Singleton

/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */
@Module
@Singleton
class DefaultDaggerModule {

    @Provides
    @Singleton
    public fun provideGson(): Gson {
        return GsonBuilder().setExclusionStrategies(SerializedNameOnlyStrategy()).create()
    }

    @Provides
    @Singleton
    public fun provideDefaultFilesystem(): FileSystem {
        return FileSystems.getDefault()
    }
}