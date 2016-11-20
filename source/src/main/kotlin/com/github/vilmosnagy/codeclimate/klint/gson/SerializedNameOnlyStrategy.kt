package com.github.vilmosnagy.codeclimate.klint.gson

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.annotations.SerializedName



/**
 * @author Vilmos Nagy  <vilmos.nagy@outlook.com>
 */
class SerializedNameOnlyStrategy : ExclusionStrategy {

    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.getAnnotation(SerializedName::class.java) == null
    }

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }
}