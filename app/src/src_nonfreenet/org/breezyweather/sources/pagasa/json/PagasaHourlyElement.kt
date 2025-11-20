package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyElement(
    @SerialName("@attributes") val attributes: PagasaHourlyAttributes?,
)
