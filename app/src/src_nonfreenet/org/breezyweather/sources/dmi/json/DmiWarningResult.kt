package org.breezyweather.sources.dmi.json

import kotlinx.serialization.Serializable

@Serializable
data class DmiWarningResult(
    val locationWarnings: List<DmiWarning>? = null,
)
