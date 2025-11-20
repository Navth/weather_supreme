package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyTabular(
    val time: List<PagasaHourlyTime>?,
)
