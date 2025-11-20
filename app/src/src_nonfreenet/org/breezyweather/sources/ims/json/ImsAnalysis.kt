package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsAnalysis(
    @SerialName("weather_code") val weatherCode: String?,
    @SerialName("relative_humidity") val relativeHumidity: String?,
    @SerialName("due_point_Temp") val dewPointTemp: String?,
    val temperature: String?,
    @SerialName("wind_direction_id") val windDirectionId: String?,
    @SerialName("wind_speed") val windSpeed: Int?,
    @SerialName("wind_chill") val windChill: String?,
    // weather_code
    @SerialName("feels_like") val feelsLike: String?,
    @SerialName("u_v_index") val uvIndex: String?,
)
