package org.breezyweather.sources.smg.json

import kotlinx.serialization.Serializable

@Serializable
data class SmgWarningRoot(
    val Custom: List<SmgWarningCustom>?,
)
