package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastElementValue(
    @SerialName("Temperature") val temperature: String?,
    @SerialName("MaxTemperature") val maxTemperature: String?,
    @SerialName("MinTemperature") val minTemperature: String?,
    @SerialName("DewPoint") val dewPoint: String?,
    @SerialName("ApparentTemperature") val apparentTemperature: String?,
    @SerialName("MaxApparentTemperature") val maxApparentTemperature: String?,
    @SerialName("MinApparentTemperature") val minApparentTemperature: String?,
    @SerialName("RelativeHumidity") val relativeHumidity: String?,
    @SerialName("WindDirection") val windDirection: String?,
    @SerialName("WindSpeed") val windSpeed: String?,
    @SerialName("ProbabilityOfPrecipitation") val probabilityOfPrecipitation: String?,
    @SerialName("Weather") val weather: String?,
    @SerialName("WeatherCode") val weatherCode: String?,
    @SerialName("UVIndex") val uvIndex: String?,
)
