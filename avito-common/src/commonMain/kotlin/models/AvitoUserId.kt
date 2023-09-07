package models

import kotlin.jvm.JvmInline

@JvmInline
value class AvitoUserId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = AvitoUserId("")
    }
}