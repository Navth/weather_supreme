package org.breezyweather.sources.dmi.json

import kotlinx.serialization.Serializable

@Serializable
data class DmiResult(
    val id: String? = null,
    val city: String? = null,
    val country: String? = null,
    val timezone: String? = null,
    val sunrise: String? = null,
    val sunset: String? = null,
    val timeserie: List<DmiTimeserie>? = null,
)
