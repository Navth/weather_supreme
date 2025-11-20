package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastResult(
    @SerialName("merged_forecast") val mergedForecast: List<MetIeForecastHourly>? = null,
)
