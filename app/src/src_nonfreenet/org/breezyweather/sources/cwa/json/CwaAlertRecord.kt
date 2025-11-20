package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertRecord(
    val datasetInfo: CwaAlertDatasetInfo?,
    val contents: CwaAlertContents?,
    val hazardConditions: CwaAlertHazardConditions?,
)
