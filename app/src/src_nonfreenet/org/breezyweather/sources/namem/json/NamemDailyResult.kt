package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemDailyResult(
    @SerialName("fore_5day") val fore5Day: List<NamemDailyForecast>? = null,
)
