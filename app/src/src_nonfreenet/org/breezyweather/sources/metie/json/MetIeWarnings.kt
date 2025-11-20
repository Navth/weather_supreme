package org.breezyweather.sources.metie.json

import kotlinx.serialization.Serializable

@Serializable
data class MetIeWarnings(
    val national: List<MetIeWarning>?,
)
