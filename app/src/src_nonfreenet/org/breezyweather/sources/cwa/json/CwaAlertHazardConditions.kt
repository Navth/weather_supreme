package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertHazardConditions(
    val hazards: CwaAlertHazards?,
)
