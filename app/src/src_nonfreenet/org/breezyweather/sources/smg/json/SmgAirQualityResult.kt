package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgAirQualityResult(
    val pohopolu: SmgAirQuality? = null,
    val enhopolu: SmgAirQuality? = null,
    val tchopolu: SmgAirQuality? = null,
    val tghopolu: SmgAirQuality? = null,
    val cdhopolu: SmgAirQuality? = null,
    val khhopolu: SmgAirQuality? = null,
)
