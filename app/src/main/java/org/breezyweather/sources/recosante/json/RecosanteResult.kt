package org.breezyweather.sources.recosante.json

import kotlinx.serialization.Serializable

@Serializable
data class RecosanteResult(
    val raep: RecosanteRaep?,
)
