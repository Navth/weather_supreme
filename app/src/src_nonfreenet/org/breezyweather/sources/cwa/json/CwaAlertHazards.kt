package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertHazards(
    val hazard: List<CwaAlertHazard>?,
)
