package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaCurrentResult(
    val records: CwaCurrentRecords? = null,
)
