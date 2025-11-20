package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgForecastResult(
    val data: List<BmkgForecastData>? = null,
)
