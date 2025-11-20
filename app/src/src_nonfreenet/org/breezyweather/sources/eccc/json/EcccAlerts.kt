package org.breezyweather.sources.eccc.json

import kotlinx.serialization.Serializable

@Serializable
data class EcccAlerts(
    val alerts: List<EcccAlert>?,
)
