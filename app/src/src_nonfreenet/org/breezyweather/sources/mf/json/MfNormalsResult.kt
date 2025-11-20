package org.breezyweather.sources.mf.json

import kotlinx.serialization.Serializable

/**
 * Mf normals
 */
@Serializable
data class MfNormalsResult(
    val geometry: MfGeometry? = null,
    val properties: MfNormalsProperties? = null,
)
