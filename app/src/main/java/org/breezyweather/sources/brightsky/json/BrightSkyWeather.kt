package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class BrightSkyWeather(
    @Serializable(DateSerializer::class) val timestamp: Date,
    val icon: String?,
    val precipitation: Double?, // Previous hour
    @SerialName("precipitation_probability") val precipitationProbability: Int?, // Previous hour
    val temperature: Double?, // At timestamp
    @SerialName("wind_direction") val windDirection: Int?, // Previous hour
    @SerialName("wind_speed") val windSpeed: Double?, // Previous hour
    @SerialName("wind_gust_direction") val windGustDirection: Int?, // Previous hour
    @SerialName("wind_gust_speed") val windGustSpeed: Double?, // Previous hour
    @SerialName("cloud_cover") val cloudCover: Int?, // At timestamp
    @SerialName("dew_point") val dewPoint: Double?, // At timestamp
    @SerialName("relative_humidity") val relativeHumidity: Int?, // At timestamp
    val pressure: Double?, // At timestamp
    val visibility: Int?, // At timestamp
    val sunshine: Double?, // Previous hour, in minutes
)
