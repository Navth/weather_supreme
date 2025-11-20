package org.breezyweather.sources.namem.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NamemHourlyResult(
    @SerialName("fore_3hours") val fore3Hours: List<NamemHourlyForecast>? = null,
)
