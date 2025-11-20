package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaAssistantDataSet(
    val parameterSet: CwaAssistantParameterSet? = null,
)
