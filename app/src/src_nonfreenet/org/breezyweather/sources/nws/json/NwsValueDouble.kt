package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsValueDouble(
    val validTime: String, // Convert to Date + interval later
    val value: Double?,
)
