package models

import kotlin.jvm.JvmInline

@JvmInline
value class OfferId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = OfferId("")
    }
}