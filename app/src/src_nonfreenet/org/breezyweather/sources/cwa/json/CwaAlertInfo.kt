package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertInfo(
    val phenomena: String?,
    val significance: String?,
    val affectedAreas: CwaAlertAffectedAreas?,
)
