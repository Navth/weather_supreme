package org.breezyweather.sources.ims.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImsHourly(
    val hour: String,
    @SerialName("weather_code") val weatherCode: String?,
    @SerialName("rain_chance") val rainChance: String?,
    @SerialName("precise_temperature") val preciseTemperature: String?,
    @SerialName("relative_humidity") val relativeHumidity: String?,
    @SerialName("wind_direction_id") val windDirectionId: String?,
    @SerialName("wind_speed") val windSpeed: Int?,
    @SerialName("wind_chill") val windChill: String?,
    @SerialName("u_v_index") val uvIndex: String?,
)
