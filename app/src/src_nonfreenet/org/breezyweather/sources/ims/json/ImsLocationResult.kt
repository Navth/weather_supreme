package org.breezyweather.sources.ims.json

import kotlinx.serialization.Serializable

@Serializable
data class ImsLocationResult(
    val data: Map<String, ImsLocation>?,
)
