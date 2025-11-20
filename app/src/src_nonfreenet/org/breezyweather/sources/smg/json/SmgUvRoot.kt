package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgUvRoot(
    val Custom: List<SmgUvCustom>?,
)
