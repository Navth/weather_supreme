package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertRecords(
    val record: List<CwaAlertRecord>?,
)
