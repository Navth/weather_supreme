package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyResult(
    val forecast: PagasaHourlyForecast? = null,
)
