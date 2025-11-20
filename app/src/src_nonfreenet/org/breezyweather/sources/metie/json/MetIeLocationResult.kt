package org.breezyweather.sources.metie.json

import kotlinx.serialization.Serializable

@Serializable
data class MetIeLocationResult(
    val city: String?,
    val province: String?,
    val county: String?,
)
