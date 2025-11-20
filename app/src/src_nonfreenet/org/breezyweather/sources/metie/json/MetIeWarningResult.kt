package org.breezyweather.sources.metie.json

import kotlinx.serialization.Serializable

@Serializable
data class MetIeWarningResult(
    val warnings: MetIeWarnings? = null,
)
