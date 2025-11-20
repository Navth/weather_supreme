package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class AtmoPointPolluant(
    val polluant: String?,
    val horaires: List<AtmoPointHoraire>?,
)
