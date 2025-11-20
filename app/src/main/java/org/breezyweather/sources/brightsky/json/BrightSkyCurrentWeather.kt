package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.breezyweather.common.serializer.DateSerializer
import java.util.Date

@Serializable
data class BrightSkyCurrentWeather(
    @Serializable(DateSerializer::class) val timestamp: Date?,
    val icon: String?,
    val temperature: Double?,
    @SerialName("wind_direction_10") val windDirection: Int?,
    @SerialName("wind_speed_10") val windSpeed: Double?,
    @SerialName("wind_gust_direction_10") val windGustDirection: Int?,
    @SerialName("wind_gust_speed_10") val windGustSpeed: Double?,
    @SerialName("cloud_cover") val cloudCover: Int?,
    @SerialName("dew_point") val dewPoint: Double?,
    @SerialName("relative_humidity") val relativeHumidity: Int?,
    @SerialName("pressure_msl") val pressure: Double?,
    val visibility: Int?,
)
