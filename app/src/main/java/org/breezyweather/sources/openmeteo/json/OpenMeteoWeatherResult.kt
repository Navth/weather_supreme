package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Open-Meteo weather
 */
@Serializable
data class OpenMeteoWeatherResult(
    @SerialName("current") val current: OpenMeteoWeatherCurrent? = null,
    val daily: OpenMeteoWeatherDaily? = null,
    val hourly: OpenMeteoWeatherHourly? = null,
    @SerialName("minutely_15") val minutelyFifteen: OpenMeteoWeatherMinutely? = null,
    val error: Boolean? = null,
    val reason: String? = null,
)
