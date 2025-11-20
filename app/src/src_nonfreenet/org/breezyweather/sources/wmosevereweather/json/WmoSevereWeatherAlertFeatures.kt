package org.breezyweather.sources.wmosevereweather.json

import kotlinx.serialization.Serializable

@Serializable
data class WmoSevereWeatherAlertFeatures(
    val id: String?,
    val properties: WmoSevereWeatherAlertProperties?,
)
