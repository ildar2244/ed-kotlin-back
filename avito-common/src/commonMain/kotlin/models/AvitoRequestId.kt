package models

import kotlin.jvm.JvmInline

@JvmInline
value class AvitoRequestId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = AvitoRequestId("")
    }
}