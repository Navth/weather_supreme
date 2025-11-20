package org.breezyweather.sources.metie.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetIeForecastSymbol(
    @SerialName("@number") val number: String?,
    val description: String?,
)
