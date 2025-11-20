package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaForecastResult(
    val records: CwaForecastRecords? = null,
)
