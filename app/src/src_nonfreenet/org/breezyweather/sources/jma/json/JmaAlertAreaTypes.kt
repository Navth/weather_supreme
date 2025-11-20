package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaAlertAreaTypes(
    val areas: List<JmaAlertArea>?,
)
