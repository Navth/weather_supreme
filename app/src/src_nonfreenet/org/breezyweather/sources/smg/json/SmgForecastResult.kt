package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgForecastResult(
    @SerialName("SevenDaysForecast") val forecast: SmgForecastRoot? = null,
)
