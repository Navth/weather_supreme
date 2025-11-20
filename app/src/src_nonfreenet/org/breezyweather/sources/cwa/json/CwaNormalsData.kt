package org.breezyweather.sources.cwa.json

import kotlinx.serialization.Serializable

@Serializable
data class CwaNormalsData(
    val surfaceObs: CwaNormalsSurfaceObs?,
)
