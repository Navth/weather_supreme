package org.breezyweather.sources.pirateweather.json

import kotlinx.serialization.Serializable

@Serializable
data class PirateWeatherMinutely(
    val time: Long,
    val precipType: String?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val precipIntensityError: Double?,
)
