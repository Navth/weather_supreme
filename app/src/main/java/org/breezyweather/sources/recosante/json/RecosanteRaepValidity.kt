package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class RecosanteRaepValidity(
    val start: String?,
    val end: String?,
)
