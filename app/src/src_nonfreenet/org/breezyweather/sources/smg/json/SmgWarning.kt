package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgWarning(
    val Warncode: List<String>?,
    val Action: List<String>?,
    val IssuedAt: List<String>?,
    val Description: List<String>?,
    val Misc: List<String>?,
)
