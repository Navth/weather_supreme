package org.breezyweather.sources.accu.json

import kotlinx.serialization.Serializable

/**
 * Accu climo summary
 */
@Serializable
data class AccuClimoSummaryResult(
    val Normals: AccuClimoNormals? = null,
)
