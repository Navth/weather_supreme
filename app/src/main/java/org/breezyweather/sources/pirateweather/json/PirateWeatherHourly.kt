package org.breezyweather.sources.pirateweather.json

import kotlinx.serialization.Serializable

@Serializable
data class PirateWeatherHourly(
    val time: Long,
    val icon: String?,
    val summary: String?,

    val precipType: String?,
    val precipIntensity: Double?,
    val precipProbability: Double?,
    val precipIntensityError: Double?,
    val precipAccumulation: Double?,
    val liquidAccumulation: Double?,
    val snowAccumulation: Double?,
    val iceAccumulation: Double?,

    val temperature: Double?,
    val apparentTemperature: Double?,
    val dewPoint: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windBearing: Double?,
    val uvIndex: Double?,
    val cloudCover: Double?,
    val visibility: Double?,
)
