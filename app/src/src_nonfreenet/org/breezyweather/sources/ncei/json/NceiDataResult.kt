package org.breezyweather.sources.ncei.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NceiDataResult(
    @SerialName("DATE") val date: String,
    @SerialName("STATION") val station: String,
    @SerialName("TMAX") val tMax: String?,
    @SerialName("TMIN") val tMin: String?,
)
