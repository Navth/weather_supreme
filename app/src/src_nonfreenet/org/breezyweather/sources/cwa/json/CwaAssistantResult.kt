package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAssistantResult(
    val cwaopendata: CwaAssistantOpenData? = null,
    val cwbopendata: CwaAssistantOpenData? = null,
)
