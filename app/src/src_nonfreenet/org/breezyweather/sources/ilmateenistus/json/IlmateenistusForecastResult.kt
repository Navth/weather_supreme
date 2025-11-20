package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecastResult(
    val location: String? = null,
    val forecast: IlmateenistusForecast? = null,
)
