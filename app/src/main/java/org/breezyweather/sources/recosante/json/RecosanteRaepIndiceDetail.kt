package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class RecosanteRaepIndiceDetail(
    val indice: RecosanteRaepIndiceDetailIndice,
    val label: String,
)
