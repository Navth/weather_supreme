package org.breezyweather.sources.nws.json

import kotlinx.serialization.Serializable

@Serializable
data class NwsValueIntContainer(
    val values: List<NwsValueInt>?,
)
