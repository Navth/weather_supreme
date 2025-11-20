package org.breezyweather.sources.jma.json

import kotlinx.serialization.Serializable

@Serializable
data class JmaAreasResult(
    val centers: Map<String, JmaArea>? = null,
    val offices: Map<String, JmaArea>? = null,
    val class10s: Map<String, JmaArea>? = null,
    val class15s: Map<String, JmaArea>? = null,
    val class20s: Map<String, JmaArea>? = null,
)
