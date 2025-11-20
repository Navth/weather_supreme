package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaLocationTown(
    val townCode: String?,
    val ctyName: String?,
    val townName: String,
    val villageName: String?,
)
