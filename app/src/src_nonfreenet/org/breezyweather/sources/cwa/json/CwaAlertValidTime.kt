package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertValidTime(
    val startTime: String,
    val endTime: String,
)
