package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaCurrentWeatherElement(
    @SerialName("Weather") val weather: String?,
    @SerialName("WindDirection") val windDirection: Double?,
    @SerialName("WindSpeed") val windSpeed: Double?,
    @SerialName("AirTemperature") val airTemperature: Double?,
    @SerialName("RelativeHumidity") val relativeHumidity: Double?,
    @SerialName("AirPressure") val airPressure: Double?,
    @SerialName("GustInfo") val gustInfo: CwaCurrentGustInfo?,
)
