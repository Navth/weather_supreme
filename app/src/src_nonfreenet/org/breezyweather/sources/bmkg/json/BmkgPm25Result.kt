package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgPm25Result(
    val LAT: Double?,
    val LON: Double?,
    val PM25: Double?,
)
