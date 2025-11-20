package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class RecosanteRaep(
    val indice: RecosanteRaepIndice?,
    val validity: RecosanteRaepValidity?,
)
