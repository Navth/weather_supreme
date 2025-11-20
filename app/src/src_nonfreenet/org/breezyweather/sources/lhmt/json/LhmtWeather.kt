package org.breezyweather.sources.lhmt.json

import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateUtcSerializer
import java.util.Date

@Serializable
data class LhmtWeather(
    val forecastTimeUtc:
    @Serializable(DateUtcSerializer::class)
    Date?,
    val observationTimeUtc:
    @Serializable(DateUtcSerializer::class)
    Date?,
    val airTemperature: Double?,
    val feelsLikeTemperature: Double?,
    val windSpeed: Double?,
    val windGust: Double?,
    val windDirection: Double?,
    val cloudCover: Double?,
    val seaLevelPressure: Double?,
    val relativeHumidity: Double?,
    val totalPrecipitation: Double?,
    val conditionCode: String?,
)
