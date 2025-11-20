package org.breezyweather.sources.metno.json

import kotlinx.serialization.Serializable

/**
 * MET Norway alerts.
 */
@Serializable
data class MetNoAlertResult(
    val features: List<MetNoAlert>? = null,
)
