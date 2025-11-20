package org.breezyweather.sources.pirateweather.json

import kotlinx.serialization.Serializable

@Serializable
data class PirateWeatherForecastResult(
    val latitude: Double?,
    val longitude: Double?,
    val timezone: String?,
    val offset: Double?,
    val elevation: Int?,
    val currently: PirateWeatherCurrently?,
    val minutely: PirateWeatherForecast<PirateWeatherMinutely>?,
    val hourly: PirateWeatherForecast<PirateWeatherHourly>?,
    val daily: PirateWeatherForecast<PirateWeatherDaily>?,
    val alerts: List<PirateWeatherAlert>?,
)
