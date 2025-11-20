package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsResult(
    val records: CwaNormalsRecords? = null,
)
