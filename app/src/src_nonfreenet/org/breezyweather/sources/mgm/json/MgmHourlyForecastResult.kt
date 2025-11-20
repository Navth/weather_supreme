package org.breezyweather.sources.mgm.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MgmHourlyForecastResult(
    @SerialName("tahmin") val forecast: List<MgmHourlyForecast>? = null,
)
