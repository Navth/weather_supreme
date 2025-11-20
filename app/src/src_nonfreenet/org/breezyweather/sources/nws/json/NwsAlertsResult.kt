package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsAlertsResult(
    val features: List<NwsAlert>? = null,
)
