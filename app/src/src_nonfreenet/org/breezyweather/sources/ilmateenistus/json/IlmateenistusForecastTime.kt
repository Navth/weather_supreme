package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecastTime(
    @SerialName("@attributes") val attributes: IlmateenistusForecastAttributes,
    val phenomen: IlmateenistusForecastItem?,
    val precipitation: IlmateenistusForecastItem?,
    val windDirection: IlmateenistusForecastItem?,
    val windSpeed: IlmateenistusForecastItem?,
    val temperature: IlmateenistusForecastItem?,
    val pressure: IlmateenistusForecastItem?,
)
