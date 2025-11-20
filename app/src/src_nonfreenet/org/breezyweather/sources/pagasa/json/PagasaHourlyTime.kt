package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyTime(
    @SerialName("@attributes") val attributes: PagasaHourlyAttributes?,
    val symbol: PagasaHourlyElement?,
    val precipitation: PagasaHourlyElement?,
    val windDirection: PagasaHourlyElement?,
    val windSpeed: PagasaHourlyElement?,
    val temperature: PagasaHourlyElement?,
    val relativeHumidity: PagasaHourlyElement?,
)
