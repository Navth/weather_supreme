package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastHourly(
    val date: String,
    val localTime: String,
    val temperature: MetIeForecastValue?,
    val windDirection: MetIeForecastWindDirection?,
    val windSpeed: MetIeForecastWindSpeed?,
    val windGust: MetIeForecastWindSpeed?,
    val humidity: MetIeForecastValue?,
    val pressure: MetIeForecastValue?,
    val cloudiness: MetIeForecastPercent?,
    val precipitation: MetIeForecastPrecipitation?,
    val symbol: MetIeForecastSymbol?,
    @SerialName("feels_like") val feelsLike: Int?,
)
