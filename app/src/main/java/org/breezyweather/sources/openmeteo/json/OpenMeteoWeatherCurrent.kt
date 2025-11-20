package org.breezyweather.sources.openmeteo.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenMeteoWeatherCurrent(
    @SerialName("temperature_2m") val temperature: Double?,
    @SerialName("apparent_temperature") val apparentTemperature: Double?,
    @SerialName("weather_code") val weatherCode: Int?,
    @SerialName("wind_speed_10m") val windSpeed: Double?,
    @SerialName("wind_direction_10m") val windDirection: Double?,
    @SerialName("wind_gusts_10m") val windGusts: Double?,
    @SerialName("uv_index") val uvIndex: Double?,
    @SerialName("relative_humidity_2m") val relativeHumidity: Int?,
    @SerialName("dew_point_2m") val dewPoint: Double?,
    @SerialName("pressure_msl") val pressureMsl: Double?,
    @SerialName("cloud_cover") val cloudCover: Int?,
    val visibility: Double?,
    val time: Long,
)
