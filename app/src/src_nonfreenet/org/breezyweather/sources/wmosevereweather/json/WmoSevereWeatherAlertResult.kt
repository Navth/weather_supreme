package org.breezyweather.sources.wmosevereweather.json

import kotlinx.serialization.Serializable

@Serializable
data class WmoSevereWeatherAlertResult(
    val features: List<WmoSevereWeatherAlertFeatures>?,
)
