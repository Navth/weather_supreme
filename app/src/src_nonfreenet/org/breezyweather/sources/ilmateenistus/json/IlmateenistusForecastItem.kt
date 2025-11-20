package org.breezyweather.sources.ilmateenistus.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IlmateenistusForecastItem(
    @SerialName("@attributes") val attributes: IlmateenistusForecastAttributes,
)
