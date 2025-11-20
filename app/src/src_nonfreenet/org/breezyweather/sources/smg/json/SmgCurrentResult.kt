package org.breezyweather.sources.smg.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SmgCurrentResult(
    @SerialName("ActualWeather") val Weather: SmgCurrentRoot? = null,
)
