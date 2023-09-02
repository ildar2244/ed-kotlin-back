package models

import kotlin.jvm.JvmInline

@JvmInline
value class AdUserId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = AdUserId("")
    }
}