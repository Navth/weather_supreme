package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgForecastWeatherForecast(
    val ValidFor: List<String>?,
    val f_time: List<String>?,
    val Temperature: List<SmgValue>?,
    val Humidity: List<SmgValue>?,
    val Windspd: List<SmgValue>?,
    val Winddiv: List<SmgValue>?,
    @SerialName("Weatherstatus") val hourlyWeatherStatus: List<SmgValue>?,
    @SerialName("WeatherStatus") val dailyWeatherStatus: List<String>?,
    val WeatherDescription: List<String>?,
)
