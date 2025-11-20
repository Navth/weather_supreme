package org.breezyweather.sources.meteoam.json

import kotlinx.serialization.Serializable

@Serializable
data class MeteoAmReverseLocationResult(
    val results: List<MeteoAmReverseLocation>?,
)
