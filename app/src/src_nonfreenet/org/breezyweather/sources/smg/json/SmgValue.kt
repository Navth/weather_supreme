package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgValue(
    val Type: List<String>?,
    val Value: List<String>?,
    val dValue: List<String>?,
    val Degree: List<String>?,
)
