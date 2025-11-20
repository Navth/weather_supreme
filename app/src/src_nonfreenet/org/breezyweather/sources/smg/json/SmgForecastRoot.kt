package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgForecastRoot(
    val Custom: List<SmgForecastCustom>?,
)
