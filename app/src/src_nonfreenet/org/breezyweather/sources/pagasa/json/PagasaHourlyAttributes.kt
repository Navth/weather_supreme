package org.breezyweather.sources.pagasa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagasaHourlyAttributes(
    val from: String?,
    @SerialName("var") val symbol: String?,
    val value: String?,
    val deg: String?,
    val mps: String?,
)
