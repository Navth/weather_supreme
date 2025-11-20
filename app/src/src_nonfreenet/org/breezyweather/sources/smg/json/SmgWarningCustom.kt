package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgWarningCustom(
    val TropicalCyclone: List<SmgWarning>?,
    val Rainstorm: List<SmgWarning>?,
    val Monsoon: List<SmgWarning>?,
    val Thunderstorm: List<SmgWarning>?,
    val Stormsurge: List<SmgWarning>?,
    val Tsunami: List<SmgWarning>?,
)
