package com.github.vilmosnagy.codeclimate.klint.model

import com.github.vilmosnagy.codeclimate.klint.dagger.AppCtx
import com.google.gson.annotations.SerializedName
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */
data class Config (
        @SerializedName("include_paths") private val includedPathsAsString: List<String>
) {

    val includedPaths: List<Path> by lazy {
        includedPathsAsString.map { AppCtx.get.fileSystem.getPath(it) }
    }
}