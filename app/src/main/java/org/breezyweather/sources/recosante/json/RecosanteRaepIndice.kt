package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class RecosanteRaepIndice(
    val details: List<RecosanteRaepIndiceDetail>?,
)
