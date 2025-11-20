package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsSurfaceObs(
    val location: List<CwaNormalsLocation>?,
)
