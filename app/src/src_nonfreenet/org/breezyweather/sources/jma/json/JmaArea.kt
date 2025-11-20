package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaArea(
    val name: String?,
    val enName: String?,
    val parent: String?,
)
