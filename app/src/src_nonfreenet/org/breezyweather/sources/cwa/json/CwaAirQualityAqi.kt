package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaAirQualityAqi(
    @SerialName("pm2_5") val pm25: String?,
    val pm10: String?,
    val o3: String?,
    val no2: String?,
    val so2: String?,
    val co: String?,
)
