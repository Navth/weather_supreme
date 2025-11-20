package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecastTabular(
    val time: List<IlmateenistusForecastTime>?,
)
