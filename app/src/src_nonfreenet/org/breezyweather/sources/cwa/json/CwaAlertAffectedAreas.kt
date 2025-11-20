package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertAffectedAreas(
    val location: List<CwaAlertLocation>?,
)
