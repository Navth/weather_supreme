package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAssistantOpenData(
    val dataset: CwaAssistantDataSet? = null,
)
