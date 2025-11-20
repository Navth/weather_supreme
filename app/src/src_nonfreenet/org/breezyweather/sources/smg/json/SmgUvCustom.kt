package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgUvCustom(
    val ActualUVBReport: List<SmgUvReport>?,
)
