package models

import kotlin.jvm.JvmInline

@JvmInline
value class AdsRequestId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = AdsRequestId("")
    }
}