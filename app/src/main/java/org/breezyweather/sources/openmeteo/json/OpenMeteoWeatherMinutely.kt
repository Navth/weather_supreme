package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.Serializable

@Serializable
data class OpenMeteoWeatherMinutely(
    val time: LongArray,
    // @SerialName("precipitation_probability") val precipitationProbability: Array<Int?>?,
    val precipitation: Array<Double?>?,
)
