package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecast(
    val tabular: IlmateenistusForecastTabular?,
)
