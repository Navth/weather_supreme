package org.breezyweather.sources.atmo.json

import kotlinx.serialization.Serializable

@Serializable
data class AtmoFrancePollenFeature(
    val properties: AtmoFrancePollenProperties?,
)
