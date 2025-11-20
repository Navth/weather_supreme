package org.breezyweather.sources.hko.json

import kotlinx.serialization.Serializable

@Serializable
data class HkoCurrentRegionalWeather(
    val ObsTime: String?,
    val Temp: HkoCurrentTemp?,
    val RH: HkoCurrentRH?,
    val Wind: HkoCurrentWind?,
    val Pressure: HkoCurrentPressure?,
)
