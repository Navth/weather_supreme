package org.breezyweather.sources.pirateweather.json

import kotlinx.serialization.Serializable

@Serializable
data class PirateWeatherCurrently(
    val time: Long,
    val icon: String?,
    val summary: String?,

    val precipType: String?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val precipIntensityError: Double?,

    val temperature: Double?,
    val apparentTemperature: Double?,

    val dewPoint: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windBearing: Double?,
    val cloudCover: Double?,
    val uvIndex: Double?,
    val visibility: Double?,
)
