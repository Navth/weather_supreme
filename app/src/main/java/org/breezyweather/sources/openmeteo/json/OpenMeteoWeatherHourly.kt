package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("ktlint")
@Serializable
data class OpenMeteoWeatherHourly(
    val time: LongArray,
    @SerialName("temperature_2m") val temperature: Array<Double?>?,
    @SerialName("apparent_temperature") val apparentTemperature: Array<Double?>?,
    @SerialName("precipitation_probability") val precipitationProbability: Array<Int?>?,
    val precipitation: Array<Double?>?,
    val rain: Array<Double?>?,
    val showers: Array<Double?>?,
    val snowfall: Array<Double?>?,
    @SerialName("weather_code") val weatherCode: Array<Int?>?,
    @SerialName("wind_speed_10m") val windSpeed: Array<Double?>?,
    @SerialName("wind_direction_10m") val windDirection: Array<Int?>?,
    @SerialName("wind_gusts_10m") val windGusts: Array<Double?>?,
    @SerialName("uv_index") val uvIndex: Array<Double?>?,
    @SerialName("is_day") val isDay: IntArray?, /* Should be a boolean (true or false) but API returns an integer */
    @SerialName("relative_humidity_2m") val relativeHumidity: Array<Int?>?,
    @SerialName("dew_point_2m") val dewPoint: Array<Double?>?,
    @SerialName("pressure_msl") val pressureMsl: Array<Double?>?,
    @SerialName("cloud_cover") val cloudCover: Array<Int?>?,
    val visibility: Array<Double?>?,
)
