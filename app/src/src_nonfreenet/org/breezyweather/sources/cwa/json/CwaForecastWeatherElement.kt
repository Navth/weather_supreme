package org.breezyweather.sources.cwa.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastWeatherElement(
    @SerialName("ElementName") val elementName: String?,
    @SerialName("Time") val time: List<CwaForecastTime>?,
)
