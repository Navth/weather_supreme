package org.breezyweather.sources.bmkg.json

import kotlinx.serialization.Serializable

@Serializable
data class BmkgForecastData(
    val cuaca: List<List<BmkgCuaca>>?,
)
