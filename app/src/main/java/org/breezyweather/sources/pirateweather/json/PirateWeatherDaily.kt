package org.breezyweather.sources.pirateweather.json

import kotlinx.serialization.Serializable

@Serializable
data class PirateWeatherDaily(
    val time: Long,
    val icon: String?,
    val summary: String?,

    val precipType: String?,
    val precipIntensity: Double?,
    val precipIntensityMax: Double?,
    val precipIntensityMaxTime: Long?,
    val precipProbability: Double?,
    val precipAccumulation: Double?,

    val temperatureHigh: Double?,
    val temperatureHighTime: Long?,
    val temperatureLow: Double?,
    val temperatureLowTime: Long?,
    val apparentTemperatureHigh: Double?,
    val apparentTemperatureHighTime: Long?,
    val apparentTemperatureLow: Double?,
    val apparentTemperatureLowTime: Long?,

    val temperatureMin: Double?,
    val temperatureMinTime: Long?,
    val temperatureMax: Double?,
    val temperatureMaxTime: Long?,
    val apparentTemperatureMin: Double?,
    val apparentTemperatureMinTime: Long?,
    val apparentTemperatureMax: Double?,
    val apparentTemperatureMaxTime: Long?,

    val dewPoint: Double?,
    val humidity: Double?,
    val pressure: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windGustTime: Long?,
    val windBearing: Double?,
    val cloudCover: Double?,
    val uvIndex: Double?,
    val uvIndexTime: Long?,
    val visibility: Double?,
)
