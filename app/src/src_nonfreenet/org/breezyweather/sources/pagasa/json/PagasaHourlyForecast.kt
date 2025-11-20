package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyForecast(
    val tabular: PagasaHourlyTabular?,
)
