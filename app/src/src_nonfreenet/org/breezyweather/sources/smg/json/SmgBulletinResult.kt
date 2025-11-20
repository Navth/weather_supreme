package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgBulletinResult(
    @SerialName("ActualForecast") val Forecast: SmgBulletinRoot? = null,
)
