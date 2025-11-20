package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAlertDatasetInfo(
    val datasetDescription: String?,
    val validTime: CwaAlertValidTime,
)
