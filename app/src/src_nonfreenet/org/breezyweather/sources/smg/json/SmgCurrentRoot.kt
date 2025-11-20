package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgCurrentRoot(
    val Custom: List<SmgCurrentCustom>?,
)
