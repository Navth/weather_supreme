package org.breezyweather.sources.brightsky.json

import kotlinx.serialization.Serializable

@Serializable
data class BrightSkyAlertsResult(
    val alerts: List<BrightSkyAlert>? = null,
)
